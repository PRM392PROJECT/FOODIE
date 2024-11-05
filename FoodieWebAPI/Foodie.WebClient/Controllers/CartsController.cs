using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers;

[CheckLoginCookie("Customer")]
public class CartsController : Controller
{
    // GET
    public IActionResult Index()
    {
        int userId = int.Parse(Request.Cookies["UserId"]);
        return View(userId);
    }
}