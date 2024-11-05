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

        [HttpGet("/Products/Index")]
        public IActionResult Index()
        {
            var userEmail = Request.Cookies["UserEmail"];
            ViewBag.IsLoggedIn = userEmail != null;
            return View();
        }
        [CheckLoginCookie("Customer")]
        [HttpGet("/Products/Detail/{productId}")]
        public IActionResult Detail([FromRoute] int productId)
        {
            var userId = Request.Cookies["UserId"];
            ViewBag.UserId = int.Parse(userId);
            return View(productId);
        }
    }
}