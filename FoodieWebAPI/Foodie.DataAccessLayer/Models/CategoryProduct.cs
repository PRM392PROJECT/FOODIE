using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Foodie.DataAccessLayer.Models
{
    public partial class CategoryProduct
    {
        public CategoryProduct()
        {
            Products = new HashSet<Product>();
        }

        public int CategoryId { get; set; }
        public string CategoryName { get; set; } = null!;
        [JsonIgnore] public virtual ICollection<Product> Products { get; set; }
    }
}