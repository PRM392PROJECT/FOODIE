using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers
{
    public class ProductsController : Controller
    {
        private HttpClient _httpClient;
        public ProductsController(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }
        public IActionResult Index()
        {
            var userEmail = Request.Cookies["UserEmail"];
            ViewBag.IsLoggedIn = userEmail != null;
            return View();
        }
        
        public IActionResult Detail(int productId)
        {
            return View();
        }

    }
}
