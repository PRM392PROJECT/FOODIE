using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers
{
    public class OrdersController : Controller
    {
        [HttpGet("Orders/{productId}")]
        public IActionResult Index([FromRoute] int productId)
        {
            return View(productId);
        }
    }
}