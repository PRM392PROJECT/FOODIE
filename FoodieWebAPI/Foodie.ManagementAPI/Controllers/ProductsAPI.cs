using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Foodie.Service.FileManager;
using Foodie.Service.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/products")]
    [ApiController]
    public class ProductsAPI : ControllerBase
    {
        private readonly IFileService _fileService;

        private readonly IProductRepository _productRepository;

        private readonly IMapper _mapper;

        public ProductsAPI(IFileService fileService, IProductRepository productRepository, IMapper mapper)
        {
            _fileService = fileService;
            _productRepository = productRepository;
            _mapper = mapper;
        }

        //// create image of product ok
        //[HttpPost("images/upload/{productId}")]
        //public async Task<IActionResult> UploadFile([FromRoute] int productId,[FromForm] int orderIndex, [FromForm] FileUploadViewModel file)
        //{
        //    FileUploadViewModel fileUp ;
        //    try
        //    {
        //        fileUp = await _fileService.SaveFile(file);
        //        if(fileUp != null)
        //        {
        //            var image = new ProductImage()
        //            {
        //                ProductId = productId,
        //                OrderIndex = orderIndex,
        //                ImageUrl = fileUp.FilePath
        //            };
        //            await _productRepository.CreateImage(image);
        //        }
        //        return Ok(fileUp);
        //    }
        //    catch (Exception ex)
        //    {
        //        await _fileService.DeleteFile(file.FilePath);
        //        return BadRequest(ex.Message);
        //    }
        //}

        // Create product /create ok
        [HttpPost("create")]
        public async Task<IActionResult> CreateProduct([FromBody] ProductRequest productRequest)
        {
            try
            {
                var product = _mapper.Map<Product>(productRequest);
                product = await _productRepository.CreateProduct(product);
                var productRp = _mapper.Map<ProductResponse>(product);
                return Ok(product);
            } catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        // Get product page
        [HttpGet("get-page")]
        [AllowAnonymous]
        public async Task<IActionResult> GetPageProduct([FromQuery] int categoryId = 0, [FromQuery] int pageNumber = 1, [FromQuery] int pageSize = 10)
        {
            try
            {
                var products = await _productRepository.GetProductsByCategory(categoryId, pageNumber, pageSize);
                var totalsItem = await _productRepository.CountProductByCategoryId(categoryId);
                var _items = _mapper.Map<List<ProductResponse>>(products);
                var page = new ViewPage<ProductResponse>(pageNumber, pageSize, _items, totalsItem);
                return Ok(page);
            }
            catch (UnauthorizedAccessException)
            {
                return Unauthorized("Bạn không có quyền truy cập tài nguyên này");
            }
       
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }
        [Authorize(Roles = "Saler")]
        [HttpGet("get-by-restaurantId/{restaurantId}")]
        public async Task<IActionResult> GetPageProductOfRestaurant([FromRoute] int restaurantId = 0, [FromQuery] int pageNumber = 1, [FromQuery] int pageSize = 10)
        {
            try
            {
                var products = await _productRepository.GetProductsByRestaurantId(restaurantId, pageNumber, pageSize);
                var totalsItem = await _productRepository.CountProductByRestaurentId(restaurantId);
                var _items = _mapper.Map<List<ProductResponse>>(products);
                var page = new ViewPage<ProductResponse>(pageNumber, pageSize, _items, totalsItem);
                return Ok(page);
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }
        // detail product ok
        [AllowAnonymous]
        [HttpGet("get-byId/{productId}")]
        public async Task<IActionResult> GetProductById([FromRoute] int productId)
        {
            try
            {
                var product = await _productRepository.GetProductById(productId);
                var producrResponse = _mapper.Map<ProductResponse>(product);
                if (product != null)
                {
                    return Ok(producrResponse);
                }
                return NotFound("Can not found product id: " + productId);
            } catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        // search by name ok
        [AllowAnonymous]
        [HttpGet("search-by-name/{productName}")]
        public async Task<IActionResult> SearchProductByName([FromRoute] string productName)
        {
            try
            {
                var products = await _productRepository.GetProductByName(productName, 1, int.MaxValue);
                var items = _mapper.Map<List<ProductResponse>>(products);
                var page = new ViewPage<ProductResponse>(1,int.MaxValue,items,items.Count());
                return Ok(page);
            }catch(Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("update-product/{productId}")]
        public async Task<IActionResult> UpdateProduct([FromRoute] int productId,[FromBody] ProductRequest request)
        {
            try
            {
                var product = _mapper.Map<Product>(request);
                product.ProductId = productId;
                product = await _productRepository.UpdateProduct(productId, product);
                var pRq = _mapper.Map<ProductResponse>(product);
                return Ok(pRq);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.ToString());
            }
        }
        
    }
}
