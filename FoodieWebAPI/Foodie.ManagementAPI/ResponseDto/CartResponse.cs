using Foodie.DataAccessLayer.Models;

namespace Foodie.ManagementAPI.ResponseDto;

public class CartResponse
{
    public int CartId { get; set; }
    public int UserId { get; set; }
    public DateTime? CreatedAt { get; set; }
    public virtual ICollection<CartItemResponse> CartItems { get; set; }
}