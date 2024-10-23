namespace Foodie.ManagementAPI.ResponseDto
{
    public class UserResponse
    {
        public UserResponse() { }
        public int UserId { get; set; }
        public string FirstName { get; set; } = null!;
        public string LastName { get; set; } = null!;
        public string PhoneNumber { get; set; } = null!;
        public string? Email { get; set; }
        public string? Address { get; set; }
        public DateTime? CreateAt { get; set; }
        public DateTime? UpDateAt { get; set; }
        public string? AvatarUrl { get; set; }
        public int RoleId { get; set; }
        public string RoleName { get; set; }
        public int ?RestaurantId { get; set; }
    }
}
