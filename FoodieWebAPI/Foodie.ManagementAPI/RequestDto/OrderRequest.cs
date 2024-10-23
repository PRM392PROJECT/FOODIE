using System.ComponentModel.DataAnnotations;

namespace Foodie.ManagementAPI.RequestDto
{
    public class OrderRequest
    {
        public int OrderId { get; set; }
        [Required]
        public int UserId { get; set; }
        [Required]
        public int RestaurantId { get; set; }
        [Range(0.1, double.MaxValue)]
        public decimal TotalAmount { get; set; }
        [Required]
        [Range(1,4)]
        public int Status { get; set; }
        public ICollection<OrderItemRequest> ?OrderItems { get; set; }
    }
}
