using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/orders")]
    [ApiController]
    public class OrdersAPI : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly IOrderRepository _orderRepository;

        public OrdersAPI(IMapper mapper, IOrderRepository orderRepository)
        {
            _mapper = mapper;
            _orderRepository = orderRepository;
        }

        [HttpPost("create-order")]
        public async Task<IActionResult> CreateOrder([FromBody] OrderRequest orderRequest)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var order = _mapper.Map<Order>(orderRequest);
                order = await _orderRepository.CreateOrder(order);
                var oderResponse = _mapper.Map<OrderResponse>(order);
                return Ok(oderResponse);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("get-order-by-userId/{userId}")]
        public async Task<IActionResult> GetOrderByUserId(int userId)
        {
            try
            {
                var orders = await _orderRepository.GetOrdersByUserId(userId);
                var orderResponses = _mapper.Map<List<OrderResponse>>(orders);
                orderResponses = orderResponses.OrderByDescending(o => o.OrderDate).ToList();
                return Ok(orderResponses);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("get-order-of-saler/{salerId}")]
        public async Task<IActionResult> GetOrderOfSaler([FromRoute]int salerId)
        {
            try
            {
                var orders = await _orderRepository.GetOrdersBySalerId(salerId);
                var orderResponses = _mapper.Map<List<OrderResponse>>(orders);
                orderResponses = orderResponses.OrderByDescending(o => o.OrderDate).ToList();
                return Ok(orderResponses);
            }
            catch (Exception ex)
            {
                return Conflict(ex.Message);
            }
        }
    }
}