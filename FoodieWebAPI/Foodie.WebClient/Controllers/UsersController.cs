﻿using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Foodie.WebClient.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers
{
    public class UsersController : Controller
    {
        private readonly HttpClient httpClient;

        public UsersController()
        {
            httpClient = new HttpClient();
        }

        [HttpGet]
        public IActionResult Login()
        {
            var userRoleId = Request.Cookies["UserRoleId"];
            var userEmail = Request.Cookies["UserEmail"];
            if (!string.IsNullOrEmpty(userEmail) && !string.IsNullOrEmpty(userRoleId))
            {
                return GetView(int.Parse(userRoleId));
            }
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(Login login)
        {
            var result = await AuthenticateUser(login.Email, login.Password);

            if (result is OkObjectResult okResult)
            {
                var user = okResult.Value as UserResponse;
                return GetView(user.RoleId);
            }
            else if (result is BadRequestObjectResult badRequest)
            {
                var errorMessage = badRequest.Value?.ToString() ?? "Invalid login attempt.";
                ModelState.AddModelError(string.Empty, errorMessage);
            }

            login.Password = null;
            return View(login);
        }

        private async Task<IActionResult> AuthenticateUser(string email, string password)
        {
            var login = new Login() { Email = email, Password = password };
            var response = await httpClient.PostAsJsonAsync("http://localhost:7059/api/users/login",login);

            if (response.IsSuccessStatusCode)
            {
                var user = await response.Content.ReadFromJsonAsync<UserResponse>();
                var cookieOptions = new CookieOptions
                {
                    Expires = DateTime.Now.AddDays(7)
                };
                Response.Cookies.Append("UserRoleId", user.RoleId.ToString(), cookieOptions);
                Response.Cookies.Append("UserId", user.UserId.ToString(), cookieOptions);
                Response.Cookies.Append("UserEmail", user.Email, cookieOptions);
                Response.Cookies.Append("UserPhoneNumber", user.PhoneNumber, cookieOptions);
                Response.Cookies.Append("UserRestaurentId",user.RestaurantId.ToString(), cookieOptions);
                return Ok(user);
            }

            var errorMessage = await response.Content.ReadAsStringAsync();
            return BadRequest($"Error during login: {errorMessage}");
        }

        public IActionResult Logout()
        {
            Response.Cookies.Delete("UserRoleId");
            Response.Cookies.Delete("UserId");
            Response.Cookies.Delete("UserEmail");
            Response.Cookies.Delete("UserPhoneNumber");
            Response.Cookies.Delete("UserPassword");
            Response.Cookies.Delete("UserRestaurentId");
            return RedirectToAction("Login", "Users");
        }

        public IActionResult GetView(int roleId)
        {
            switch (roleId)
            {
                case 1:
                    return RedirectToAction("Index", "Products"); // Customer
                case 2:
                    return RedirectToAction("Dashboard", "Sellers"); // Seller
                case 3:
                    return RedirectToAction("Dashboard", "Admins"); // Admin
                case 4:
                    return RedirectToAction("Dashboard", "Shippers"); // Shipper
                default:
                    return RedirectToAction("Login", "Users"); // Mặc định: quay về trang đăng nhập
            }
        }
    }
}