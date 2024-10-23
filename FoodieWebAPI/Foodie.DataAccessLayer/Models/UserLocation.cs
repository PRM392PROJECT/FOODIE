using System;
using System.Collections.Generic;

namespace Foodie.DataAccessLayer.Models
{
    public partial class UserLocation
    {
        public int LocationId { get; set; }
        public int UserId { get; set; }
        public decimal Latitude { get; set; }
        public decimal Longitude { get; set; }
        public DateTime? UpdatedAt { get; set; }

        public virtual User User { get; set; } = null!;
    }
}
