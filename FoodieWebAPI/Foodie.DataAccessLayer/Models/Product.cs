using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Foodie.DataAccessLayer.Models
{
    public partial class Product
    {
        public Product()
        {
            CartItems = new HashSet<CartItem>();
            FavoriteProducts = new HashSet<FavoriteProduct>();
            OrderItems = new HashSet<OrderItem>();
            ProductFeedbacks = new HashSet<ProductFeedback>();
            ProductImages = new HashSet<ProductImage>();
        }

        public int ProductId { get; set; }
        public int RestaurantId { get; set; }
        public int CategoryId { get; set; }
        public string Name { get; set; } = null!;
        public string? Description { get; set; }
        public decimal Price { get; set; }
        public DateTime? CreateAt { get; set; }
        public DateTime? UpDateAt { get; set; }

        public virtual CategoryProduct Category { get; set; } = null!;
        public virtual Restaurant Restaurant { get; set; } = null!;
        public virtual ICollection<CartItem> CartItems { get; set; }
        public virtual ICollection<FavoriteProduct> FavoriteProducts { get; set; }
        public virtual ICollection<OrderItem> OrderItems { get; set; }
        public virtual ICollection<ProductFeedback> ProductFeedbacks { get; set; }

        public virtual ICollection<ProductImage> ProductImages { get; set; }
    }
}