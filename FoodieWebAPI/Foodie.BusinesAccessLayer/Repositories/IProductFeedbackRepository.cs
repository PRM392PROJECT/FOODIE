using Foodie.DataAccessLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public interface IProductFeedbackRepository
    {
        Task<IEnumerable<ProductFeedback>> GetFeedback(int productId,int pageNumber,int pageSize);
       
        Task<ProductFeedback> GetFeedbackById(int productId);

        Task<ProductFeedback> CreateFeedback(ProductFeedback feedback);

        Task<bool> DeleteFeedback(int productId);

        Task<int> CountByProductId(int productId);
    }
}
