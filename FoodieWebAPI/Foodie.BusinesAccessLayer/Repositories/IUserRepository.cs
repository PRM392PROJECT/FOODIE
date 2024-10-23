
using Foodie.DataAccessLayer.Models;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public interface IUserRepository
    {
        
        Task<List<User>> GetUsers(int pageNumber,int pageSize);
        
        Task<User> CreateUser(User user);
        
        Task<bool> DeleteUser(int id);
        
        Task<bool> UpdateUser(int userId,User user);
        
        Task<User> GetUserById(int id);
        
        Task<User> LoginByPhoneAndPassword(string phoneNumber, string password);
        
        Task<int>  CountUser();
        
        string HashPassword(string password);
        
        string DecodeBase64(string encodedData);
        Task<User> AuthenticateUser(string email,string password);
    }
}
