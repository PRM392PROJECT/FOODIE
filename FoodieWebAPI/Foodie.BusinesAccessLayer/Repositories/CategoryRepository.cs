using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public class CategoryRepository : ICategoryRepository
    {
        private readonly CategoryDao _categoryDao;
        
        public CategoryRepository(FOODIEContext context)
        {
            _categoryDao = new CategoryDao(context);
        }
       
        public async Task<CategoryProduct> CreateCate(CategoryProduct categoryProduct)
        {
            if (categoryProduct == null || string.IsNullOrWhiteSpace(categoryProduct.CategoryName))
            {
                throw new ArgumentException("CategoryProduct hoặc tên danh mục không hợp lệ.");
            }

            var existingCategory = await _categoryDao.GetByName(categoryProduct.CategoryName);
            if (existingCategory!=null)
            {
                throw new Exception($"Danh mục '{categoryProduct.CategoryName}' đã tồn tại.");
            }

            try
            {
                return await _categoryDao.Create(categoryProduct);
            }
            catch (Exception ex)
            {
                throw new Exception($"Lỗi khi tạo danh mục: {ex.Message}");
            }
        }

        public async Task<bool> DeleteCate(int categoryId)
        {
            try
            {
                var cate =  await _categoryDao.GetById(categoryId);
                if (cate == null) return false;
                return await _categoryDao.Delete(cate);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<IEnumerable<CategoryProduct>> GetAllCate()
        {
            try
            {
                return await _categoryDao.GetAll();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<CategoryProduct> GetCateById(int categoryId)
        {
            try
            {
                return await _categoryDao.GetById(categoryId);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<CategoryProduct> UpdateCate(int categoryId, CategoryProduct categoryProduct)
        {
            if (_categoryDao.GetByName(categoryProduct.CategoryName) != null)
            {
                throw new Exception("Category name is exit");
            }
            try
            {
                var cateExit = await _categoryDao.GetById(categoryId);
                if (cateExit != null)
                {
                    cateExit.CategoryName = categoryProduct.CategoryName;
                    await _categoryDao.Update(cateExit);
                }
                return cateExit;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }
    }
}
