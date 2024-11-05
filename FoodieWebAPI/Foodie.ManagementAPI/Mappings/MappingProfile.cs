using System.Drawing;
using AutoMapper;
using Foodie.DataAccessLayer.Models;
using Foodie.ManagementAPI.RequestDto;
using Foodie.ManagementAPI.ResponseDto;
using Microsoft.IdentityModel.Tokens;

namespace Foodie.ManagementAPI.Mappings
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            CreateMap<Product, ProductResponse>()
                .ForMember(dest => dest.CategoryName, otp => otp.MapFrom(src => src.Category.CategoryName))
                .ForMember(dest => dest.Images, otp => otp.MapFrom(src => src.ProductImages));

            CreateMap<ProductImage, ProductImageResponse>();

            CreateMap<ProductImageRequest, ProductImage>();

            CreateMap<ProductRequest, Product>()
                .ForMember(dest => dest.ProductImages, otp => otp.MapFrom(src => src.Images));
            CreateMap<ProductUpdate, Product>();
            CreateMap<UserRequest, User>()
                .ForMember(dest => dest.PasswordHash,
                    opt => opt.MapFrom(src =>
                        Convert.ToBase64String(System.Text.Encoding.UTF8.GetBytes(src.Password))));
            CreateMap<ImageUpdate, ProductImage>();
            CreateMap<RestaurantRequest, Restaurant>();

            CreateMap<Restaurant, RestaurantResponse>()
                .ForMember(dest => dest.Manager, otp => otp.MapFrom(src => src.Manager));

            CreateMap<User, UserResponse>()
                .ForMember(dest => dest.RoleName, otp => otp.MapFrom(src => src.Role.RoleName))
                .ForMember(dest => dest.RestaurantId,
                    otp => otp.MapFrom(src =>
                        src.Restaurants.IsNullOrEmpty() ? 0 : src.Restaurants.FirstOrDefault().RestaurantId));

            CreateMap<UserUpdateRequest, User>();
            CreateMap<ProductFeedback, ProductFeedbackResponse>()
                .ForMember(dest => dest.UserFirstName, otp => otp.MapFrom(src => src.User.FirstName))
                .ForMember(dest => dest.UserLastName, otp => otp.MapFrom(src => src.User.LastName))
                .ForMember(dest => dest.AvatarUrl, otp => otp.MapFrom(src => src.User.AvatarUrl));

            CreateMap<ProductFeedbackRequest, ProductFeedback>();

            CreateMap<CategoryProduct, CategoryResponse>();
            CreateMap<CategoryRequest, CategoryProduct>();

            CreateMap<OrderRequest, Order>();
            CreateMap<OrderItemRequest, OrderItem>();

            CreateMap<Order, OrderResponse>();
            CreateMap<OrderItem, OrderItemResponse>();

            CreateMap<CartItemRequest, CartItem>();
            CreateMap<CartItem, CartItemResponse>()
                .ForMember(dest=>dest.ProductName,otp=>otp.MapFrom(src=>src.Product.Name))
                .ForMember(dest=>dest.RestaurantId,otp=>otp.MapFrom(src=>src.Product.RestaurantId))
                .ForMember(dest=>dest.ProductImageUrl,
                    otp=>otp.MapFrom(src=>src.Product.ProductImages.FirstOrDefault().ImageUrl));
            CreateMap<Cart, CartResponse>();
        }
    }
}