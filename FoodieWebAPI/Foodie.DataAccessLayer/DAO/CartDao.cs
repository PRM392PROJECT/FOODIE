using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;

namespace Foodie.DataAccessLayer.DAO
{
    public class CartDao
    {
        private readonly FOODIEContext _context;

        public CartDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<int> Count()
        {
            return await _context.Carts.CountAsync();
        }

        public async Task<Cart> Create(Cart entity)
        {
            _context.Carts.Add(entity);
            await _context.SaveChangesAsync();
            return entity;
        }

        public async Task<CartItem> AddItemToCart(CartItem cartItem)
        {
            _context.CartItems.Add(cartItem);
            await _context.SaveChangesAsync();
            return cartItem;
        }

        public async Task<bool> Delete(Cart entity)
        {
            _context.Carts.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteItemById(int id)
        {
            var cartItem = await _context.CartItems.FirstOrDefaultAsync(c => c.CartItemId == id);
            if (cartItem != null)
            {
                _context.CartItems.Remove(cartItem);
                return await _context.SaveChangesAsync() > 0;
            }
            return false;
        }

        public async Task<Cart> GetById(int userId)
        {
            var cart = await _context.Carts.FirstOrDefaultAsync(c => c.UserId == userId);
            if (cart != null)
            {
                var cartItems = await _context.CartItems
                    .Include(c => c.Product)
                    .ThenInclude(p=>p.ProductImages)
                    .Where(c => c.CartId == cart.CartId)
                    .ToListAsync();
                cart.CartItems = cartItems;
            }

            return cart;
        }
        

        public async Task<bool> Update(Cart entity)
        {
            _context.Carts.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> UpdateCartItem(int cartItemId, int quantity)
        {
            var cartItem = await _context.CartItems.FirstOrDefaultAsync(c => c.CartItemId == cartItemId);
            if (cartItem != null)
            {
                cartItem.Quantity = quantity;
                return await _context.SaveChangesAsync()>0;
                
            }

            return false;
        }
    }
}