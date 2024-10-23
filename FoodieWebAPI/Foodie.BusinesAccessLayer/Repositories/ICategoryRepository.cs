using Foodie.DataAccessLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public interface ICategoryRepository
    {
        Task<CategoryProduct> CreateCate(CategoryProduct categoryProduct);
        
        Task<CategoryProduct> UpdateCate(int categoryId ,CategoryProduct categoryProduct);
        
        Task<bool> DeleteCate(int categoryId);
        
        Task<IEnumerable<CategoryProduct>> GetAllCate();
        
        Task<CategoryProduct> GetCateById(int categoryId);

    }
}
