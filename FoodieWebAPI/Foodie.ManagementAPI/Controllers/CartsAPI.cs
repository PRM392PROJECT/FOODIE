using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/carts")]
    [ApiController]
    public class CartsAPI : ControllerBase
    {
        private readonly ICartRepository _cartRepository;
        private readonly IMapper _mapper;

        public CartsAPI(ICartRepository cartRepository, IMapper mapper)
        {
            _cartRepository = cartRepository;
            _mapper = mapper;
        }

        [HttpGet("get-by-user/{userId}")]
        public async Task<IActionResult> getCartsByUserId(int userId)
        {
            try
            {
                var cart = await  _cartRepository.GetCartAsync(userId);
                var cartResponse = _mapper.Map<CartResponse>(cart);
                return Ok(cartResponse);
            }
            catch (Exception e)
            {
                return BadRequest();
            }
        }

        [HttpPost("add-cart/{userId}")]
        public async Task<IActionResult> AddCartItem([FromRoute] int userId,[FromBody] CartItemRequest cartItemRequest)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            try
            {
                var cartItem = _mapper.Map<CartItem>(cartItemRequest);
                var cart = await _cartRepository.GetCartAsync(userId);
                if (cart != null)
                {
                    cartItem.CartId = cart.CartId;
                    var result = await _cartRepository.AddToCartAsync(cartItem);
                    return Ok(result);
                }
                return NotFound();

            }
            catch (Exception ex)
            {
                return BadRequest();
            }
        }

        [HttpDelete("remove-cart-item/{id}")]
        public async Task<IActionResult> RemoveCartItem([FromRoute] int id)
        {
            try
            {
                var result = await _cartRepository.RemoveFromCartAsync(id);
                if (result) return Ok();
                return NotFound();
            }
            catch (Exception ex)
            {
                return BadRequest();
            }
        }

        [HttpPut("update-cart-item/{id}/{quantity}")]
        public async Task<IActionResult> updateCartItem([FromRoute] int id,[FromRoute]int quantity)
        {
            if(quantity<=0) return BadRequest();
            try
            {
                var result = await _cartRepository.UpdateQuantityAsync(id, quantity);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return BadRequest();
            }
        }
    }   
}

