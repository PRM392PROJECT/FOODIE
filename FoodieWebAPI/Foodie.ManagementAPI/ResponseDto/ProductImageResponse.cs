namespace Foodie.ManagementAPI.ResponseDto
{
    public class ProductImageResponse
    {
        public int ImageId { get; set; }
        public int ProductId { get; set; }
        public string ImageUrl { get; set; } = null!;
        public int OrderIndex { get; set; }
    }
}