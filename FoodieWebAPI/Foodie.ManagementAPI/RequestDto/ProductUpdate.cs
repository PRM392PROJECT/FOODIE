using System.ComponentModel.DataAnnotations;

namespace Foodie.ManagementAPI.RequestDto;

public class ProductUpdate
{
    [Microsoft.Build.Framework.Required] public int CategoryId { get; set; }

    [Microsoft.Build.Framework.Required]
    [StringLength(100, ErrorMessage = "Name cannot exceed 100 characters.")]
    [RegularExpression(@"^\S.*$", ErrorMessage = "Name cannot start with whitespace.")]
    public string Name { get; set; } = null!;

    [StringLength(255)] public string? Description { get; set; }

    [Microsoft.Build.Framework.Required]
    [Range(0.01, double.MaxValue, ErrorMessage = "Price must be greater than 0.")]
    public decimal Price { get; set; }
}