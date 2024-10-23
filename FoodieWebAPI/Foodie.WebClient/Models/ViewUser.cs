using System.ComponentModel.DataAnnotations;

namespace Foodie.WebClient.Models
{
    public class ViewUser
    {
        public int UserId { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string PhoneNumber { get; set; }

        public string? Email { get; set; }

        public string Password { get; set; }

        public string? Address { get; set; }

        public int? RoleId { get; set; }
    }
}
