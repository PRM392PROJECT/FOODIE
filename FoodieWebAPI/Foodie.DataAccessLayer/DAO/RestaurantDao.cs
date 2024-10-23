using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;

namespace Foodie.DataAccessLayer.DAO
{
    public class RestaurantDao 
    {
        private readonly FOODIEContext _context;
        public RestaurantDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<int> Count()
        {
            return await _context.Restaurants.CountAsync();
        }

        public async Task<Restaurant> Create(Restaurant entity)
        {
            await _context.Restaurants.AddAsync(entity);
            await _context.SaveChangesAsync();
            return entity;
        }

        public async Task<bool> Delete(Restaurant entity)
        {
            _context.Restaurants.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var rest = await _context.Restaurants.FirstOrDefaultAsync(r => r.RestaurantId == id);
            _context.Restaurants.Remove(rest);
            return await _context.SaveChangesAsync() >0;
        }

        public async Task<IEnumerable<Restaurant>> GetAll()
        {
            var rests = await _context.Restaurants
                .Include(r => r.Manager).ThenInclude(m => m.Role)
                .ToListAsync();
            return rests;
        }
        public async Task<IEnumerable<Restaurant>> GetRestaurants(int pageNumber,int pageSize)
        {
            var rests = await _context.Restaurants
                .Include(r => r.Manager).ThenInclude(u=>u.Role)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();
            return rests;
        }

        public async Task<Restaurant> GetById(int id)
        {
            var rests = await _context.Restaurants
                .Include(r => r.Manager).ThenInclude(u=>u.Role)
                .FirstOrDefaultAsync(r => r.RestaurantId == id);
            return rests;
        }

        public async Task<bool> Update(Restaurant entity)
        {
            _context.Restaurants.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }
    }
}
