using System.ComponentModel.DataAnnotations;

namespace Foodie.ManagementAPI.RequestDto
{
    public class UserRequest
    {
        public int UserId { get; set; }

        [Required(ErrorMessage = "First name is required.")]
        public string FirstName { get; set; }

        [Required(ErrorMessage = "Last name is required.")]
        public string LastName { get; set; }

        [Required(ErrorMessage = "Phone number is required.")]
        [Phone(ErrorMessage = "Invalid phone number format.")]
        public string PhoneNumber { get; set; }

        [Required(ErrorMessage = "Email is required.")]
        [EmailAddress(ErrorMessage = "Invalid email format.")]
        public string? Email { get; set; }

        [Required(ErrorMessage = "Password is required.")]
        [StringLength(50, MinimumLength = 6, ErrorMessage = "Password must be between 6 and 50 characters long.")]
        public string Password { get; set; }

        public string? Address { get; set; }

        [Required(ErrorMessage = "RoleId is required.")]
        [Range(1, 4, ErrorMessage = "RoleId must be between 1 and 4.")]
        public int? RoleId { get; set; }
    }
}