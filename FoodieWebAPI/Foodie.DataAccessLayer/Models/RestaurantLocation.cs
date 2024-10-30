using System;
using System.Collections.Generic;

namespace Foodie.DataAccessLayer.Models
{
    public partial class RestaurantLocation
    {
        public int LocationId { get; set; }
        public int RestaurantId { get; set; }
        public decimal Latitude { get; set; }
        public decimal Longitude { get; set; }
        public DateTime? UpdatedAt { get; set; }

        public virtual Restaurant Restaurant { get; set; } = null!;
    }
}