using Foodie.DataAccessLayer.Models;

namespace Foodie.ManagementAPI.ResponseDto
{
    public class RestaurantResponse
    {
        public RestaurantResponse()
        {
        }

        public int RestaurantId { get; set; }
        public string Name { get; set; } = null!;
        public string? Location { get; set; }
        public string? PhoneNumber { get; set; }
        public TimeSpan TimeOpen { get; set; }
        public TimeSpan TimeClose { get; set; }
        public DateTime? CreateAt { get; set; }
        public DateTime? UpDateAt { get; set; }
        public string? AvatarUrl { get; set; }
        public string? CoverImageUrl { get; set; }
        public int Status { get; set; }

        public virtual UserResponse Manager { get; set; }
    }
}