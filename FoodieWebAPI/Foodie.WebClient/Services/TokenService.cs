using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace Foodie.WebClient.Services
{
    public class TokenService
    {
        private readonly IConfiguration _configuration;

        public TokenService(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public ClaimsPrincipal DecodeToken(string token)
        {
            var jwtHandler = new JwtSecurityTokenHandler();
            var jwtToken = jwtHandler.ReadToken(token) as JwtSecurityToken;

            if (jwtToken == null)
            {
                throw new SecurityTokenException("Invalid token");
            }

            var key = Encoding.UTF8.GetBytes(_configuration["JwtSettings:SecretKey"]);
            var validationParameters = new TokenValidationParameters
            {
                ValidateIssuerSigningKey = true,
                IssuerSigningKey = new SymmetricSecurityKey(key),
                ValidateIssuer = false,
                ValidateAudience = false,
                ClockSkew = TimeSpan.Zero  // Optional: reduce the clock skew
            };

            // Validate the token and get the claims
            SecurityToken validatedToken;
            var principal = jwtHandler.ValidateToken(token, validationParameters, out validatedToken);

            return principal;
        }
    }
}
