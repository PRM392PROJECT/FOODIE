using Foodie.DataAccessLayer.Models;

namespace Foodie.BusinesAccessLayer.Repositories;

public interface ICartRepository
{
    Task<Cart> GetCartAsync(int userId);
    Task<bool> AddToCartAsync(CartItem cartItem);
    Task<bool> RemoveFromCartAsync(int cartItemId);
    Task<bool> UpdateCartAsync(CartItem cartItem);
    Task<bool> UpdateQuantityAsync(int cartItemId, int quantity);
}