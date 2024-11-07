using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Foodie.DataAccessLayer.Models
{
    public partial class User
    {
        public User()
        {
            Carts = new HashSet<Cart>();
            FavoriteProducts = new HashSet<FavoriteProduct>();
            Orders = new HashSet<Order>();
            ProductFeedbacks = new HashSet<ProductFeedback>();
            Restaurants = new HashSet<Restaurant>();
            UserLocations = new HashSet<UserLocation>();
        }

        public int UserId { get; set; }
        public string FirstName { get; set; } = null!;
        public string LastName { get; set; } = null!;
        public string PhoneNumber { get; set; } = null!;
        public string? Email { get; set; }
        public string PasswordHash { get; set; } = null!;
        public string? Address { get; set; }
        public DateTime? CreateAt { get; set; }
        public DateTime? UpDateAt { get; set; }
        public string? AvatarUrl { get; set; }
        public int? RoleId { get; set; }

        public virtual Role? Role { get; set; }
        public virtual ICollection<Cart>? Carts { get; set; }
        public virtual ICollection<FavoriteProduct>? FavoriteProducts { get; set; }
        [JsonIgnore] public virtual ICollection<Order>? Orders { get; set; }
        [JsonIgnore] public virtual ICollection<ProductFeedback>? ProductFeedbacks { get; set; }
        [JsonIgnore] public virtual ICollection<Restaurant>? Restaurants { get; set; }
        [JsonIgnore] public virtual ICollection<UserLocation>? UserLocations { get; set; }
    }
}