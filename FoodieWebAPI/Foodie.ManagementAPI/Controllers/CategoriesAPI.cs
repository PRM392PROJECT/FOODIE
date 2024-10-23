using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.OData.Query;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/categories")]
    [ApiController]
    public class CategoriesAPI : ControllerBase
    {
        private readonly ICategoryRepository _categoryRepository;
        private readonly IMapper _mapper;
        public CategoriesAPI(ICategoryRepository categoryRepository, IMapper mapper)
        {
            _categoryRepository = categoryRepository;
            _mapper = mapper;
        }
        // ok
        [HttpGet]
        [EnableQuery]
        public async Task<IActionResult> GetCategories()
        {
            try
            {
                var cates = (List<CategoryProduct>)await _categoryRepository.GetAllCate();
                var caterp = _mapper.Map<List<CategoryResponse>>(cates);
                var cateAll = new CategoryResponse() { CategoryId = 0, CategoryName = "All" };
                caterp.Add(cateAll);
                caterp = caterp.OrderBy(x => x.CategoryId).ToList();
                return Ok(caterp);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        // ok
        [HttpPost("create")]
        public async Task<IActionResult> CreateCategory([FromBody] CategoryRequest categoryRequest)
        {
            try
            {
                var cate = _mapper.Map<CategoryProduct>(categoryRequest);
                cate = await _categoryRepository.CreateCate(cate);
                var response = _mapper.Map<CategoryResponse>(cate);
                return Ok(cate);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

    }
}
