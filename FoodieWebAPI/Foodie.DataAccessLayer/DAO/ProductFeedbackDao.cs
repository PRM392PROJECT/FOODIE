using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.DataAccessLayer.DAO
{
    public class ProductFeedbackDao
    {
        private readonly FOODIEContext _context;

        public ProductFeedbackDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<ProductFeedback>> GetFeedBacks(int productId, int pageNumber, int pageSize)
        {
            var feedbacks = await _context.ProductFeedbacks
                .Include(p => p.User) // Include the User entity
                .Where(p => p.ProductId == productId)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .Select(p => new ProductFeedback
                {
                    FeedbackId = p.FeedbackId,
                    ProductId = p.ProductId,
                    UserId = p.UserId,
                    Rating = p.Rating,
                    Comment = p.Comment,
                    User = new User 
                    {
                        UserId = p.User.UserId,
                        FirstName = p.User.FirstName,
                        LastName = p.User.LastName,
                        Email = p.User.Email,
                        AvatarUrl  = p.User.AvatarUrl
                    }
                })
                .ToListAsync();

            return feedbacks;
        }


        public async Task<ProductFeedback> Create(ProductFeedback productFeedback)
        {
            await _context.ProductFeedbacks.AddAsync(productFeedback);
            await _context.SaveChangesAsync();
            return productFeedback;
        }
        public async Task<bool> Delete(int feedBackId)
        {
            var fd = await _context.ProductFeedbacks
                .FirstOrDefaultAsync(x => x.FeedbackId == feedBackId);
            if (fd == null) return false;
            _context.ProductFeedbacks.Remove(fd);
            return await _context.SaveChangesAsync()>0;
        
        }
        public async Task<int> CountByProductId(int productId)
        {
            var total = await _context.ProductFeedbacks
                .Where(x => x.ProductId == productId)
                .CountAsync();
            return total;
        }

        public async Task<ProductFeedback> GetById(int id)
        {
            var fed = await _context.ProductFeedbacks
                .Include(u=>u.User)
                .FirstOrDefaultAsync(x=>x.FeedbackId == id);
            return fed;
        }
    }
}
