using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.Extensions.Configuration;

namespace Foodie.WebClient.Controllers;

public class CheckLoginCookieAttribute : ActionFilterAttribute
{
    public readonly List<string> _requiredRoles;

    public CheckLoginCookieAttribute(params string[] requiredRoles)
    {
        _requiredRoles = requiredRoles.ToList();
    }

    public override void OnActionExecuting(ActionExecutingContext context)
    {
        var token = context.HttpContext.Request.Cookies["AuthToken"];

        if (string.IsNullOrEmpty(token))
        {
            context.Result = new RedirectToRouteResult(
                new RouteValueDictionary(new { controller = "Users", action = "Login" })
            );
        }
        else
        {
            var userRole = GetUserRoleFromToken(token);

            if (!_requiredRoles.Contains(userRole))
            {
                context.Result = new RedirectToRouteResult(
                    new RouteValueDictionary(new { controller = "Users", action = "Error" })
                );
                return;
            }

        }
        
        base.OnActionExecuting(context);
    }

    private string GetUserRoleFromToken(string token)
    {
        var handler = new JwtSecurityTokenHandler();
        if (handler.CanReadToken(token))
        {
            var jwtToken = handler.ReadJwtToken(token);
            var roleClaim = jwtToken.Claims.FirstOrDefault(c => c.Type == ClaimTypes.Role);
            return roleClaim?.Value ?? string.Empty;
        }
        return string.Empty;
    }
}