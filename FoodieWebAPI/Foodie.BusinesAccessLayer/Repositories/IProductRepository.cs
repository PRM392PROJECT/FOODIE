using Foodie.DataAccessLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public interface IProductRepository
    {
        Task<Product> CreateProduct(Product product);

        Task<Product> UpdateProduct(int productId, Product product);

        Task<bool> DeleteProductById(int productId);

        Task<Product> GetProductById(int productId);

        Task<IEnumerable<Product>> GetProductsByCategory(int categoryId, int pageNumber, int pageSize);

        Task<IEnumerable<Product>> GetProductsByRestaurantId(int restaurantId, int pageNumber, int pageSize);

        Task<IEnumerable<Product>> GetProductsByEmail(string email, int pageNumber, int pageSize);

        Task<IEnumerable<Product>> GetProductByName(string productName, int pageNumber, int pageSize);

        Task<IEnumerable<ProductImage>> CreateImages(List<ProductImage> productImages);

        Task<ProductImage> CreateImage(ProductImage productImages);

        Task<IEnumerable<ProductImage>> GetProductImage(int productId);

        Task<int> CountProductByCategoryId(int categoryId);

        Task<int> CountProductByRestaurentId(int restaurantId);

        Task<int> CountProductByEmail(string email);

        Task<ProductImage> UpdatePImage(ProductImage productImage);
        
        Task<bool> DeleteImageById(int imageId);
    }
}