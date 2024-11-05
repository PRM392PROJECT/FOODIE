using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;

namespace Foodie.BusinesAccessLayer.Repositories;

public class CartRepository : ICartRepository
{
    private readonly CartDao CartDao;

    public CartRepository(FOODIEContext context)
    {
        CartDao = new CartDao(context);
    }

    public async Task<Cart> GetCartAsync(int userId)
    {
        return await CartDao.GetById(userId);
    }

    public async Task<bool> AddToCartAsync(CartItem cartItem)
    {
        await CartDao.AddItemToCart(cartItem);
        return  true;
    }

    public async Task<bool> RemoveFromCartAsync(int cartItemId)
    {
        return await CartDao.DeleteItemById(cartItemId);
    }

    public Task<bool> UpdateCartAsync(CartItem cartItem)
    {
        throw new NotImplementedException();
    }

    public async Task<bool> UpdateQuantityAsync(int cartItemId, int quantity)
    {
        try
        {
            return  await CartDao.UpdateCartItem(cartItemId,quantity);
        }
        catch (Exception ex)
        {
            throw new Exception("false update cartItem");
        }
    }
}