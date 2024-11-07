namespace Foodie.ManagementAPI.ResponseDto
{
    public class OrderResponse
    {
        public int OrderId { get; set; }
        public int UserId { get; set; }
        public int RestaurantId { get; set; }
        public DateTime? OrderDate { get; set; }
        public decimal TotalAmount { get; set; }
        public int Status { get; set; }

        public string StatusMessage
        {
            get
            {
                return Status switch
                {
                    1 => "Processing",
                    2 => "Confirmed",
                    3 => "Shipping",
                    4 => "Completed",
                    5 => "Cancelled",
                    6 => "Returned",
                    7 => "Refunded",
                    _ => "Unknown"
                };
            }
        }

        public ICollection<OrderItemResponse> OrderItems { get; set; }
    }
}