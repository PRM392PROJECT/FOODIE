using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Foodie.DataAccessLayer.Models
{
    public partial class Cart
    {
        public Cart()
        {
            CartItems = new HashSet<CartItem>();
        }

        public int CartId { get; set; }
        public int UserId { get; set; }
        public DateTime? CreatedAt { get; set; }
        [JsonIgnore] public virtual User User { get; set; } = null!;
        public virtual ICollection<CartItem> CartItems { get; set; }
    }
}