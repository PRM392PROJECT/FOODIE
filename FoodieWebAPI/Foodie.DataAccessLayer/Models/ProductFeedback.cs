using System;
using System.Collections.Generic;

namespace Foodie.DataAccessLayer.Models
{
    public partial class ProductFeedback
    {
        public int FeedbackId { get; set; }
        public int ProductId { get; set; }
        public int UserId { get; set; }
        public int? Rating { get; set; }
        public string? Comment { get; set; }
        public DateTime? CreatedAt { get; set; } = DateTime.Now;

        public virtual Product Product { get; set; } = null!;
        public virtual User User { get; set; } = null!;
    }
}
