using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Foodie.DataAccessLayer.Models
{
    public partial class ProductImage
    {
        public int ImageId { get; set; }
        public int ProductId { get; set; }
        public string ImageUrl { get; set; } = null!;
        public int OrderIndex { get; set; }
        [JsonIgnore]
        public virtual Product Product { get; set; } = null!;
    }
}
