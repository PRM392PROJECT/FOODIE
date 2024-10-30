using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public class OrderRepository : IOrderRepository
    {
        private readonly OrderDao orderDao;

        public OrderRepository(FOODIEContext context)
        {
            orderDao = new OrderDao(context);
        }

        public async Task<Order> CreateOrder(Order order)
        {
            if (order.OrderItems.IsNullOrEmpty())
            {
                throw new ArgumentNullException("OrderItem is Null or Empty");
            }

            try
            {
                var total = order.OrderItems.Sum(o => o.Price * o.Quantity);
                order.TotalAmount = total;
                await orderDao.Create(order);
                return order;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<IEnumerable<Order>> GetOrdersByUserId(int userId)
        {
            var orders = await orderDao.GetByUserId(userId);
            return orders.Where(o => o.UserId == userId).ToList();
        }
    }
}