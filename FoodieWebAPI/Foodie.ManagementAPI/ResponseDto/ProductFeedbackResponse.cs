﻿namespace Foodie.ManagementAPI.ResponseDto
{
    public class ProductFeedbackResponse
    {
            
        public int FeedbackId { get; set; }
        public int ProductId { get; set; }
        public int UserId { get; set; }
        public int? Rating { get; set; }
        public string? Comment { get; set; }
        public DateTime? CreatedAt { get; set; }

        public string UserFirstName { get; set; } = null!;
        public string UserLastName { get; set; } = null!;
        public string? AvatarUrl { get; set; }
    }
}
