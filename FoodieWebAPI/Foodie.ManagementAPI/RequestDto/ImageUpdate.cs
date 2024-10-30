namespace Foodie.ManagementAPI.RequestDto;

public class ImageUpdate
{
    public int ImageId { get; set; }
    public string ImageUrl { get; set; } = null!;
}