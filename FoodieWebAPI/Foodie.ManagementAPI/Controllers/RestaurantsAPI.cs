using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/restaurants")]
    [ApiController]
    public class RestaurantsAPI : ControllerBase
    {
        private readonly IRestaurantRepository _restaurantRepository;
        
        private readonly IMapper _mapper;
        
        public RestaurantsAPI(IRestaurantRepository restaurantRepository,IMapper mapper)
        {
            _restaurantRepository = restaurantRepository;
            _mapper = mapper;
        }
        
        // Get restaurantpage?pageNumber=1&pageSize=10 ok
        [HttpGet("get-page")]
        public async Task<IActionResult> GetRestaurantPage([FromQuery] int pageNumber =1, [FromQuery]int pageSize=10)
        {
            try
            {
                var restaurants = await _restaurantRepository.GetRestaurants(pageNumber, pageSize);
                var totalItem = await _restaurantRepository.Count();
                var items = _mapper.Map<List<RestaurantResponse>>(restaurants);
                var page = new ViewPage<RestaurantResponse>(pageNumber, pageSize,items, totalItem);
                return Ok(page);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
       
        //Create Restaurant /create ok
        [HttpPost("create")]
        public async Task<IActionResult> CreateRestaurant([FromBody] RestaurantRequest restaurantRequest)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest();
            }
            try
            {
                var res = _mapper.Map<Restaurant>(restaurantRequest);
                res = await _restaurantRepository.Create(res);
                res = await _restaurantRepository.GetById(res.RestaurantId);
                var resreponse = _mapper.Map<RestaurantResponse>(res);
                return Ok(resreponse);
            }catch(Exception ex)
            {
                return BadRequest(ex.ToString());
            }
        }
       
        // ok
        [HttpGet("get-byId/{restaurantId}")]
        public async Task<IActionResult> GetRestaurantById([FromRoute] int restaurantId)
        {
            try
            {
                var res = await _restaurantRepository.GetById(restaurantId);
                var resResponse = _mapper.Map<RestaurantResponse>(res);
                return Ok(resResponse);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.ToString());
            }
        }
        
    }
}
