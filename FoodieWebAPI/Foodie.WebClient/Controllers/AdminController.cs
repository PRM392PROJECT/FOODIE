using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers
{
    [CheckLoginCookie("Admin")]
    public class AdminController : Controller
    {
        // GET
        [HttpGet("/admin/dashboard")]
        public IActionResult Dashboard()
        {
            return View();
        }
    }
}


