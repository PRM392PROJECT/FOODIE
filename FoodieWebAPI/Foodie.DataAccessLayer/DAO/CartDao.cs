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

        public async Task<bool> Delete(Cart entity)
        {
            _context.Carts.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var cart = _context.Carts.FirstOrDefault(c => c.CartId == id);
            if (cart != null)
            {
                _context.Carts.Remove(cart);
                return await _context.SaveChangesAsync() > 0;
            }

            return false;
        }

        public async Task<IEnumerable<Cart>> GetAll()
        {
            return await _context.Carts.ToListAsync();
        }

        public async Task<Cart> GetById(int userId)
        {
            var cart = await _context.Carts.FirstOrDefaultAsync(c => c.UserId == userId);
            if (cart != null)
            {
                var cartItems = await _context.CartItems
                    .Include(c => c.Product)
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
    }
}