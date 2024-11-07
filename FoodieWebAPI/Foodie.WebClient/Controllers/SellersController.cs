using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers
{
    [CheckLoginCookie("Saler")]
    public class SellersController : Controller
    {
        private readonly HttpClient _httpClient;

        public SellersController(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public IActionResult Dashboard()
        {
            var token = Request.Cookies["AuthToken"];
            var email = Request.Cookies["UserEmail"];
            ViewBag.Token = token;
            ViewBag.Email = email;
            return View();
        }

        [HttpGet]
        public IActionResult CreateFood()
        {
            var restaurantId = int.Parse(Request.Cookies["UserRestaurentId"]);
            ViewBag.RestaurantId = restaurantId;
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> CreateFood(ProductRequest product)
        {
            product.RestaurantId = int.Parse(Request.Cookies["UserRestaurentId"]);
            if (!ModelState.IsValid)
            {
                return View(product);
            }

            try
            {
                var response = await _httpClient.PostAsJsonAsync("http://localhost:7059/api/products/create", product);
                response.EnsureSuccessStatusCode();
                var result = await response.Content.ReadFromJsonAsync<ProductRequest>();
                return RedirectToAction("Dashboard");
            }
            catch (HttpRequestException ex)
            {
                ModelState.AddModelError(string.Empty, ex.Message);
                return View(product);
            }
        }

        [HttpGet("Sellers/EditProduct/{productId}")]
        public async Task<IActionResult> EditProduct(int productId)
        {
            return View(productId);
        }

        [HttpGet("Sellers/UpdateImageProduct/{productId}")]
        public async Task<IActionResult> UpdateImageProduct(int productId)
        {
            return View(productId);
        }

        [HttpGet("Sellers/Orders")]
        public async Task<IActionResult> Orders()
        {
            var userId = Request.Cookies["UserId"];
            ViewBag.UserId = userId;
            return View();
        }
    }
}