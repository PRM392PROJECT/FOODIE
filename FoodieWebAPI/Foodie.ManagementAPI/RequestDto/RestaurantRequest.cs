using System.ComponentModel.DataAnnotations;

namespace Foodie.ManagementAPI.RequestDto
{
    public class RestaurantRequest
    {
        public int RestaurantId { get; set; }
        [Required]
        [StringLength(100, ErrorMessage = "Name cannot exceed 100 characters.")]
        [RegularExpression(@"^\S+$", ErrorMessage = "Name cannot contain spaces.")]
        public string Name { get; set; }
        public string? Location { get; set; }
        [Required]
        [Phone(ErrorMessage = "Invalid phone number format.")]
        public string? PhoneNumber { get; set; }
        [Required]
        public TimeSpan TimeOpen { get; set; }
        [Required]
        public TimeSpan TimeClose { get; set; }
        [Required]
        public int? ManagerId { get; set; }
        public string? AvatarUrl { get; set; }
        public string? CoverImageUrl { get; set; }
        [Required]
        [Range(1, 4, ErrorMessage = "Status must be between 1 and 4.")]
        public int Status { get; set; }
    }
}
