using Microsoft.AspNetCore.Mvc;

namespace Foodie.WebClient.Controllers;

[CheckLoginCookie("Customer")]
public class CustomersController : Controller
{

    public IActionResult MyOrder()
    {
        var userId = Request.Cookies["UserId"];
        ViewBag.UserId = userId;
        return View();
    }

    [HttpGet("Customers/OrderDetail/{productId}")]
    public IActionResult OrderDetail([FromRoute] int productId)
    {
        var userId = Request.Cookies["UserId"];
        ViewBag.UserId = userId;
        return View(productId);
    }

    [HttpGet("Customers/Carts")]
    public IActionResult Carts()
    {
        int userId = int.Parse(Request.Cookies["UserId"]);
        return View(userId);
    }

    public IActionResult Profile()
    {
        var userId = Request.Cookies["UserId"];
        ViewBag.UserId = userId;
        return View();
    }

    public IActionResult EditAddress()
    {
        var userId = Request.Cookies["UserId"];
        ViewBag.UserId = userId;
        return View();
    }
}