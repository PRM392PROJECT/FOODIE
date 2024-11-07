namespace Foodie.ManagementAPI.RequestDto
{
    public class ProductImageRequest
    {
        public string ImageUrl { get; set; } = null!;
        public int OrderIndex { get; set; }
    }
}