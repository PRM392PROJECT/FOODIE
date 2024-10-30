using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using NuGet.Protocol;
using System.ComponentModel.DataAnnotations;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/users")]
    [ApiController]
    public class UsersAPI : ControllerBase
    {
        private readonly IUserRepository _userRepository;
        private readonly IConfiguration _configuration;
        private readonly IMapper _mapper;

        public UsersAPI(IConfiguration configuration, IUserRepository userRepository, IMapper mapper)
        {
            _configuration = configuration;
            _userRepository = userRepository;
            _mapper = mapper;
        }

        //GET /userpage?pageNumber=1&pageSize=10 ok
        [Authorize(Roles = "Admin")]
        [HttpGet("get-page")]
        public async Task<IActionResult> UserViewPage([FromQuery] int pageNumber = 1, [FromQuery] int pageSize = 10)
        {
            try
            {
                var users = await _userRepository.GetUsers(pageNumber, pageSize);
                var items = _mapper.Map<List<UserResponse>>(users);
                var total = await _userRepository.CountUser();
                var userPage = new ViewPage<UserResponse>(pageNumber, pageSize, items, total);
                return Ok(userPage);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        // Create user /create ok
        [HttpPost("create")]
        public async Task<IActionResult> CreateUser([FromBody] UserRequest userRequest)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var user = _mapper.Map<User>(userRequest);
                var newUser = await _userRepository.CreateUser(user);
                var userResponse = _mapper.Map<UserResponse>(newUser);
                return Ok(userResponse);
            }
            catch (Exception ex)
            {
                return BadRequest($"Error creating user: {ex.Message}");
            }
        }

        // Update user /update not ok
        [HttpPut("update")]
        public async Task<IActionResult> UpdateUser([FromBody] UserUpdateRequest userUpdateRequest)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest();
            }

            try
            {
                var user = _mapper.Map<User>(userUpdateRequest);
                var success = await _userRepository.UpdateUser(userUpdateRequest.UserId, user);
                if (success)
                {
                    user = await _userRepository.GetUserById(userUpdateRequest.UserId);
                    var userResponse = _mapper.Map<UserResponse>(user);
                    return Ok(userResponse);
                }

                return BadRequest();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        //Detail user /get-byId ok
        [Authorize(Policy = "Customer")]
        [HttpGet("get-byId/{userId}")]
        public async Task<IActionResult> DetailUser([FromRoute] int userId)
        {
            try
            {
                var user = await _userRepository.GetUserById(userId);
                var userResponse = _mapper.Map<UserResponse>(user);
                return Ok(userResponse);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        //Login user /login
        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] Login login)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var user = await _userRepository.AuthenticateUser(login.Email, login.Password);
                var userResponse = _mapper.Map<UserResponse>(user);
                return Ok(userResponse);
            }
            catch (Exception ex)
            {
                return BadRequest($"Error during login: {ex.Message}");
            }
        }

        [HttpPost("authen/login")]
        public async Task<IActionResult> AuthenUser([FromBody] Login login)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var user = await _userRepository.AuthenticateUser(login.Email, login.Password);

                if (user == null)
                {
                    return Unauthorized(new { Message = "Invalid login attempt." });
                }

                var userResponse = _mapper.Map<UserResponse>(user);
                var tokenString = GenerateJwtToken(userResponse);
                var token = new Token(tokenString, DateTime.UtcNow.AddDays(7), userResponse); // Token hết hạn sau 1 giờ
                return Ok(token);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        private string GenerateJwtToken(UserResponse user)
        {
            var jwtSettings = _configuration.GetSection("JwtSettings");

            var claims = new[]
            {
                new Claim(JwtRegisteredClaimNames.Sub, user.FirstName + user.LastName),
                new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
                new Claim(ClaimTypes.Role, user.RoleName),
                new Claim("User", user.ToJson())
            };

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(jwtSettings["SecretKey"]));
            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            var token = new JwtSecurityToken(
                issuer: jwtSettings["Issuer"],
                audience: jwtSettings["Audience"],
                claims: claims,
                expires: DateTime.Now.AddMinutes(double.Parse(jwtSettings["ExpiryMinutes"])),
                signingCredentials: creds
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }
    }
}