using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Foodie.DataAccessLayer.DAO
{
    public class OrderDao
    {
        private readonly FOODIEContext _context;

        public OrderDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<int> Count()
        {
            return await _context.Orders.CountAsync();
        }

        public async Task<Order> Create(Order entity)
        {
            await _context.Orders.AddAsync(entity);
            await _context.SaveChangesAsync();
            return entity;
        }

        public async Task<bool> Delete(Order entity)
        {
            _context.Orders.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var order = await _context.Orders.FirstOrDefaultAsync(o => o.OrderId == id);
            if (order != null)
            {
                _context.Orders.Remove(order);
                return await _context.SaveChangesAsync() > 0;
            }

            return false;
        }

        public async Task<IEnumerable<Order>> GetAll()
        {
            return await _context.Orders.ToListAsync();
        }  
        public async Task<IEnumerable<Order>> GetBySalerId(int salerId)
        {
   
            return await _context.Orders
                .Include(o=>o.Restaurant)
                .Include(o=>o.OrderItems).ThenInclude(p=>p.Product).ThenInclude(p=>p.ProductImages)
                .Where(o=>o.Restaurant.ManagerId == salerId && o.OrderItems.Count()!=0)
                .ToListAsync();
        }

        public async Task<Order> GetById(int id)
        {
            return await _context.Orders.FirstOrDefaultAsync(o => o.OrderId == id);
        }

        public async Task<bool> Update(Order entity)
        {
            _context.Orders.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<IEnumerable<Order>> GetByUserId(int userId)
        {
            var orders = await _context.Orders
                .Include(o => o.OrderItems).ThenInclude(p => p.Product).ThenInclude(p => p.ProductImages)
                .Where(o => o.UserId == userId)
                .ToListAsync();
            return orders;
        }
    }
}