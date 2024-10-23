using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Foodie.DataAccessLayer.DAO
{
    public class CategoryDao 
    {
        private readonly FOODIEContext _context;

        public CategoryDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<int> Count()
        {
            return await _context.CategoryProducts.CountAsync();
        }

        public async Task<CategoryProduct> Create(CategoryProduct entity)
        {
            _context.CategoryProducts.Add(entity);
            await  _context.SaveChangesAsync();
            return entity;
        }

        public async Task<bool> Delete(CategoryProduct entity)
        {
            _context.CategoryProducts.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var category = _context.CategoryProducts.FirstOrDefault(c => c.CategoryId == id);
            if (category != null)
            {
                _context.CategoryProducts.Remove(category);
                return await _context.SaveChangesAsync() > 0;
            }
            return false;
        }

        public async Task<IEnumerable<CategoryProduct>>  GetAll()
        {
            return await _context.CategoryProducts.ToListAsync();

        }

        public async Task<CategoryProduct>  GetById(int id)
        {
            return await _context.CategoryProducts.FirstOrDefaultAsync(c => c.CategoryId == id);

        }

        public async Task<bool> Update(CategoryProduct entity)
        {
            _context.CategoryProducts.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<CategoryProduct> GetByName(string name)
        {
            name = name.ToLower().Trim();
            var cate = await _context.CategoryProducts
                .SingleOrDefaultAsync(c => c.CategoryName.ToLower().Equals(name));
            return cate;
        }
    }
}
