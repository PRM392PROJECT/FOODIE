using Foodie.DataAccessLayer.Models;

namespace Foodie.ManagementAPI.ResponseDto
{
    public class ProductResponse
    {
        public int ProductId { get; set; }
        public int RestaurantId { get; set; }
        public int CategoryId { get; set; }
        public string ?CategoryName { get; set; }
        public string Name { get; set; } = null!;
        public string? Description { get; set; }
        public decimal Price { get; set; }
        public DateTime? CreateAt { get; set; }
        public DateTime? UpDateAt { get; set; }

        public List<ProductImageResponse> Images { get; set; }
    }
}
