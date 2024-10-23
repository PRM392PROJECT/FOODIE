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
    public class ProductImageDao
    {
        private readonly FOODIEContext _context;
        public ProductImageDao(FOODIEContext context) 
        {
            _context = context;
        }
        public async Task<IEnumerable<ProductImage>> CreateRange(List<ProductImage> images)
        {
            await _context.ProductImages.AddRangeAsync(images);
            await _context.SaveChangesAsync();
            return images;
        }
        public async Task<ProductImage> Create(ProductImage images)
        {
            await _context.ProductImages.AddAsync(images);
            await _context.SaveChangesAsync();
            return images;
        }
        public async Task<IEnumerable<ProductImage>> Updates(List<ProductImage> images)
        {
             _context.ProductImages.UpdateRange(images);
             await _context.SaveChangesAsync();
            return images;
        }
        public async Task<bool> Delete(ProductImage productImage)
        {
            _context.ProductImages.Remove(productImage);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<IEnumerable<ProductImage>> GetByProductId(int productId)
        {
            var images = await _context.ProductImages
                .OrderBy(x => x.OrderIndex)
                .Where(x=>x.ProductId == productId)
                .ToListAsync();
            return images;
        }
    }
}
