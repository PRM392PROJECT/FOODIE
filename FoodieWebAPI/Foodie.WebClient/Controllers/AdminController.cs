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
            var token = Request.Cookies["AuthToken"];
            ViewBag.Token = token;
            return View();
        }
    }
}


