using System.ComponentModel.DataAnnotations;

namespace Foodie.ManagementAPI.RequestDto
{
    public class OrderItemRequest
    {
        [Required]
        public int ProductId { get; set; }
        [Required]
        [Range(1, int.MaxValue)]
        public int Quantity { get; set; }
        [Required]
        [Range(0.1, double.MaxValue)]
        public decimal Price { get; set; }

    }
}
