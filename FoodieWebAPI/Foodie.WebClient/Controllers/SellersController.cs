using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers
{
    public class SellersController : Controller
    {
        private readonly HttpClient _httpClient;
        public SellersController(HttpClient httpClient) 
        {
            _httpClient = httpClient;
        }
        public IActionResult Dashboard()
        {
            return View();
        }
        [HttpGet]
        public IActionResult CreateFood()
        {
            ProductRequest p = new ProductRequest();
            p.RestaurantId = int.Parse(Request.Cookies["UserRestaurentId"]);
            return View(p);
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
            var response = await _httpClient.GetAsync($"http://localhost:7059/api/products/get-byId/{productId}");
            if (response.IsSuccessStatusCode)
            {
                var product = await response.Content.ReadFromJsonAsync<ProductResponse>();
                return View(product);
            }
            return View();
        }

        //[HttpPost("Sellers/EditProduct/{productId}")]
        public async Task<IActionResult> EditProduct(ProductResponse productResponse)
        {
            return View(productResponse);
        }
    }
}
