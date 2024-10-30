using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;
using System.Xml.Linq;

namespace Foodie.DataAccessLayer.DAO
{
    public class ProductDao
    {
        private readonly FOODIEContext _context;

        public ProductDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<int> CountByCategoryId(int categoryId)
        {
            int total = await _context.Products
                .Where(p => categoryId == 0 || p.CategoryId == categoryId)
                .CountAsync();
            return total;
        }

        public async Task<int> CountByRestaurantId(int restaurantId)
        {
            int total = await _context.Products
                .Where(p => restaurantId == 0 || p.RestaurantId == restaurantId)
                .CountAsync();
            return total;
        }

        public async Task<int> CountByRestaurantEmail(string email)
        {
            var res = await _context.Restaurants.Include(u => u.Manager)
                .FirstOrDefaultAsync(u => u.Manager.Email == email);
            int total = await _context.Products
                .Where(p => p.RestaurantId == res.RestaurantId)
                .CountAsync();
            return total;
        }

        public async Task<Product> Create(Product entity)
        {
            _context.Products.Add(entity);
            await _context.SaveChangesAsync();
            return entity;
        }

        public async Task<bool> Delete(Product entity)
        {
            _context.Products.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var product = await _context.Products.FindAsync(id);
            if (product != null)
            {
                _context.Products.Remove(product);
                return await _context.SaveChangesAsync() > 0;
            }

            return false;
        }

        public async Task<IEnumerable<Product>> GetAll()
        {
            return await _context.Products.ToListAsync();
        }

        public async Task<Product> GetById(int id)
        {
            return await _context.Products
                .Include(p => p.Category)
                .Include(p => p.Restaurant)
                .Include(p => p.ProductImages)
                .FirstOrDefaultAsync(p => p.ProductId == id);
        }

        public async Task<bool> Update(Product entity)
        {
            _context.Products.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<IEnumerable<Product>> GetByName(string name, int pageNumber, int pageSize)
        {
            name = name.ToLower();
            var products = await _context.Products
                .Include(p => p.Category)
                .Include(p => p.Restaurant)
                .Include(p => p.ProductImages)
                .Where(p => p.Name.ToLower().Contains(name))
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();
            return products;
        }

        public async Task<IEnumerable<Product>> GetProductByCategory(int categoryId, int pageNumber, int pageSize)
        {
            var products = await _context.Products
                .Include(p => p.Category)
                .Include(p => p.Restaurant)
                .Include(p => p.ProductImages)
                .Where(p => categoryId == 0 || p.CategoryId == categoryId)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();
            return products;
        }

        public async Task<IEnumerable<Product>> GetProductByRestaurent(int restaurentId, int pageNumber, int pageSize)
        {
            var products = await _context.Products
                .Include(p => p.Category)
                .Include(p => p.Restaurant)
                .Include(p => p.ProductImages)
                .Where(p => restaurentId == 0 || p.RestaurantId == restaurentId)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();
            return products;
        }

        public async Task<IEnumerable<Product>> GetProductByRestaurent(string email, int pageNumber, int pageSize)
        {
            var res = await _context.Restaurants.Include(u => u.Manager)
                .FirstOrDefaultAsync(m => m.Manager.Email == email);
            if (res != null)
            {
                var products = await _context.Products
                    .Include(p => p.Category)
                    .Include(p => p.Restaurant)
                    .Include(p => p.ProductImages)
                    .Where(p => p.RestaurantId == res.RestaurantId)
                    .Skip((pageNumber - 1) * pageSize)
                    .Take(pageSize)
                    .ToListAsync();
                return products;
            }

            throw new Exception("Can not found ");
        }
    }
}