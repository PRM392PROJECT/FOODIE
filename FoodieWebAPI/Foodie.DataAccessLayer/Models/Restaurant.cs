using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Foodie.DataAccessLayer.Models
{
    public partial class Restaurant
    {
        public Restaurant()
        {
            Orders = new HashSet<Order>();
            Products = new HashSet<Product>();
            RestaurantLocations = new HashSet<RestaurantLocation>();
        }

        public int RestaurantId { get; set; }
        public string Name { get; set; } = null!;
        public string? Location { get; set; }
        public string? PhoneNumber { get; set; }
        public TimeSpan TimeOpen { get; set; }
        public TimeSpan TimeClose { get; set; }
        public DateTime? CreateAt { get; set; }
        public DateTime? UpDateAt { get; set; }
        public int? ManagerId { get; set; }
        public string? AvatarUrl { get; set; }
        public string? CoverImageUrl { get; set; }
        public int Status { get; set; }

        public virtual User? Manager { get; set; }
        [JsonIgnore]
        public virtual ICollection<Order> ?Orders { get; set; }
        [JsonIgnore]
        public virtual ICollection<Product> ?Products { get; set; }
        [JsonIgnore]
        public virtual ICollection<RestaurantLocation> ?RestaurantLocations { get; set; }
    }
}
