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
}