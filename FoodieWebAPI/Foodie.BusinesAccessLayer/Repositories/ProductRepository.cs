using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public class ProductRepository : IProductRepository
    {
        private readonly ProductDao _productDao;

        private readonly CategoryDao _categoryDao;

        private readonly ProductImageDao _productImageDao;

        public ProductRepository(FOODIEContext context)
        {
            _productDao = new ProductDao(context);
            _categoryDao = new CategoryDao(context);
            _productImageDao = new ProductImageDao(context);
        }

        public async Task<Product> CreateProduct(Product product)
        {
            try
            {
                return await _productDao.Create(product);
            }
            catch (Exception ex)
            {
                throw new Exception("Error create product: " + ex.Message);
            }
        }

        public async Task<bool> DeleteProductById(int productId)
        {
            try
            {
                return await _productDao.DeleteById(productId);
            }
            catch (Exception ex)
            {
                throw new Exception("Error delete product: " + ex.Message);
            }
        }

        public async Task<Product> GetProductById(int productId)
        {
            try
            {
                var product = await _productDao.GetById(productId);
                if (product != null)
                {
                    product.ProductImages = (ICollection<ProductImage>)await _productImageDao.GetByProductId(productId);
                }

                return product;
            }
            catch (Exception ex)
            {
                throw new Exception("Error create product: " + ex.Message);
            }
        }

        public async Task<IEnumerable<Product>> GetProductByName(string productName, int pageNumber, int pageSize)
        {
            try
            {
                var products = await _productDao.GetByName(productName, pageNumber, pageSize);
                return products;
            }
            catch (Exception ex)
            {
                throw new Exception("Error get product by name: " + ex.Message);
            }
        }

        public async Task<IEnumerable<Product>> GetProductsByCategory(int categoryId, int pageNumber, int pageSize)
        {
            try
            {
                var products = await _productDao.GetProductByCategory(categoryId, pageNumber, pageSize);
                return products;
            }
            catch (Exception ex)
            {
                throw new Exception("Error get product by category: " + ex.Message);
            }
        }

        public async Task<Product> UpdateProduct(int productId, Product product)
        {
            try
            {
                var productExit = await _productDao.GetById(productId);
                if (productExit != null)
                {
                    productExit.Name = product.Name;
                    productExit.Description = product.Description;
                    productExit.CategoryId = product.CategoryId;
                    productExit.Price = product.Price;
                    productExit.UpDateAt = DateTime.Now;
                    await _productDao.Update(productExit);
                    return product;
                }

                throw new Exception("Can not found product id: " + productId);
            }
            catch (Exception ex)
            {
                throw new Exception("Error update product: " + ex.Message);
            }
        }

        public Task<IEnumerable<ProductImage>> CreateImages(List<ProductImage> productImages)
        {
            try
            {
                return null;
            }
            catch (Exception ex)
            {
                throw new Exception("");
            }
        }

        public async Task<ProductImage> CreateImage(ProductImage productImage)
        {
            try
            {
                productImage = await _productImageDao.Create(productImage);
                return productImage;
            }
            catch (Exception ex)
            {
                throw new Exception("Error create image: " + ex.Message);
            }
        }

        public async Task<int> CountProductByCategoryId(int categoryId)
        {
            return await _productDao.CountByCategoryId(categoryId);
        }

        public async Task<IEnumerable<ProductImage>> GetProductImage(int productId)
        {
            try
            {
                var images = await _productImageDao.GetByProductId(productId);
                return images;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<IEnumerable<Product>> GetProductsByRestaurantId(int restaurantId, int pageNumber,
            int pageSize)
        {
            try
            {
                var products = await _productDao.GetProductByRestaurent(restaurantId, pageNumber, pageSize);
                return products;
            }
            catch (Exception ex)
            {
                throw new Exception("Error get product by category: " + ex.Message);
            }
        }

        public async Task<IEnumerable<Product>> GetProductsByEmail(string email, int pageNumber, int pageSize)
        {
            try
            {
                var products = await _productDao.GetProductByRestaurent(email, pageNumber, pageSize);
                return products;
            }
            catch (Exception ex)
            {
                throw new Exception("Error get product by category: " + ex.Message);
            }
        }

        public async Task<int> CountProductByRestaurentId(int restaurantId)
        {
            return await _productDao.CountByRestaurantId(restaurantId);
        }

        public async Task<int> CountProductByEmail(string email)
        {
            return await _productDao.CountByRestaurantEmail(email);
        }

        public async Task<ProductImage> UpdatePImage(ProductImage productImage)
        {
            var image = await _productImageDao.GetById(productImage.ImageId);
            if (image != null)
            {
                image.ImageUrl = productImage.ImageUrl;
                return await _productImageDao.Update(image);
            }

            return null;
        }
    }
}