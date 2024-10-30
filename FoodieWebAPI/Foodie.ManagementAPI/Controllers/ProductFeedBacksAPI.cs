using AutoMapper;
using Foodie.BusinesAccessLayer.Repositories;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.AspNetCore.Mvc;

namespace Foodie.ManagementAPI.Controllers
{
    [Route("api/productfeedbacks")]
    [ApiController]
    public class ProductFeedBacksAPI : ControllerBase
    {
        private readonly IProductFeedbackRepository _productFeedbackRepository;

        private readonly IMapper _mapper;

        public ProductFeedBacksAPI(IProductFeedbackRepository productFeedbackRepository, IMapper mapper)
        {
            _productFeedbackRepository = productFeedbackRepository;
            _mapper = mapper;
        }

        // ok
        [HttpGet("get-page")]
        public async Task<IActionResult> GetPageFeedBack([FromQuery] int productId, [FromQuery] int pageNumber = 1,
            [FromQuery] int pageSIze = 5)
        {
            try
            {
                var feedbacks = await _productFeedbackRepository.GetFeedback(productId, pageNumber, pageSIze);
                var total = await _productFeedbackRepository.CountByProductId(productId);
                var items = _mapper.Map<List<ProductFeedbackResponse>>(feedbacks);
                var page = new ViewPage<ProductFeedbackResponse>(pageNumber, pageSIze, items, total);
                return Ok(page);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        // ok
        [HttpPost("create")]
        public async Task<IActionResult> CreateFeedBack([FromBody] ProductFeedbackRequest productFeedbackRequest)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(new { Message = "Invalid data submitted." });
            }

            try
            {
                var feedback = _mapper.Map<ProductFeedback>(productFeedbackRequest);
                feedback = await _productFeedbackRepository.CreateFeedback(feedback);
                feedback = await _productFeedbackRepository.GetFeedbackById(feedback.FeedbackId);
                var feedbackResponse = _mapper.Map<ProductFeedbackResponse>(feedback);
                return Ok(feedbackResponse);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}