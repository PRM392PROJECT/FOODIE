using System;
using System.Collections.Generic;

namespace Foodie.DataAccessLayer.Models
{
    public partial class Order
    {
        public Order()
        {
            Invoices = new HashSet<Invoice>();
            OrderItems = new HashSet<OrderItem>();
        }

        public int OrderId { get; set; }
        public int UserId { get; set; }
        public int RestaurantId { get; set; }
        public DateTime? OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public int Status { get; set; }

        public virtual Restaurant Restaurant { get; set; } = null!;
        public virtual User User { get; set; } = null!;
        public virtual ICollection<Invoice> Invoices { get; set; }
        public virtual ICollection<OrderItem> OrderItems { get; set; }
    }
}