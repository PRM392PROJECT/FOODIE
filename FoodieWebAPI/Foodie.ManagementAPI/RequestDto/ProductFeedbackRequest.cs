using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.ResponseDto;

namespace Foodie.ManagementAPI.RequestDto
{
    public class ProductFeedbackRequest
    {
        public int FeedbackId { get; set; }
        public int ProductId { get; set; }
        public int UserId { get; set; }
        public int? Rating { get; set; }
        public string? Comment { get; set; }

    }
}
