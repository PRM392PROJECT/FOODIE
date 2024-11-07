using System.ComponentModel.DataAnnotations;

namespace Foodie.ManagementAPI.RequestDto;

public class CartItemRequest
{
    
    public int ProductId { get; set; }
    [Range(1, int.MaxValue)]
    public int Quantity { get; set; }
    public decimal Price { get; set; }
}