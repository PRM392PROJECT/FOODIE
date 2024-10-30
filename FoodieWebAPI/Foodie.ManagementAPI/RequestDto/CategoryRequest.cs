namespace Foodie.ManagementAPI.RequestDto
{
    public class CategoryRequest
    {
        public int CategoryId { get; set; }
        public string CategoryName { get; set; } = null!;
    }
}