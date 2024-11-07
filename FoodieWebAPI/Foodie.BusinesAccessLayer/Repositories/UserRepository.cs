using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using System.Text;

namespace Foodie.BusinesAccessLayer.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly UserDao _userDao;
        private readonly CartDao _cartDao;
        public UserRepository(FOODIEContext context)
        {
            _userDao = new UserDao(context);
            _cartDao = new CartDao(context);
        }

        public async Task<List<User>> GetUsers(int pageNumber, int pageSize)
        {
            try
            {
                return await _userDao.GetUsers(pageNumber, pageSize);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<User> CreateUser(User user)
        {
            // Kiểm tra xem email đã tồn tại hay chưa
            if (await _userDao.GetByEmail(user.Email) != null)
            {
                throw new Exception("Email already exists.");
            }

            if (await _userDao.GetByPhoneNumber(user.PhoneNumber) != null)
            {
                throw new Exception("Phone number already exists.");
            }

            if (!IsValidPassword(user.PasswordHash))
            {
                throw new Exception("Password does not meet complexity requirements.");
            }

            if (!IsValidFirstName(user.FirstName))
            {
                throw new Exception("First name is invalid.");
            }

            if (!IsValidLastName(user.LastName))
            {
                throw new Exception("Last name is invalid.");
            }

            try
            {
                var createdUser = await _userDao.Create(user);
                return createdUser;
            }
            catch (Exception ex)
            {
                throw new Exception($"Error creating user: {ex.Message}");
            }
        }

        public async Task<bool> DeleteUser(int id)
        {
            try
            {
                return await _userDao.DeleteById(id);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<bool> UpdateUser(int userId, User user)
        {
            try
            {
                var userExit = await _userDao.GetById(userId);
                if (userExit.PhoneNumber != user.PhoneNumber)
                {
                    if (await _userDao.GetByPhoneNumber(user.PhoneNumber) != null)
                    {
                        throw new Exception("Phone number already exists.");
                    }
                }

                if (userExit.Email != user.Email)
                {
                    if (await _userDao.GetByEmail(user.Email) != null)
                    {
                        throw new Exception("Email already exists.");
                    }
                }

                userExit.FirstName = user.FirstName;
                userExit.LastName = user.LastName;
                userExit.Email = user.Email;
                userExit.Address = user.Address;
                userExit.UpDateAt = DateTime.Now;

                return await _userDao.Update(userExit);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<bool> UpdateAddress(int userId, string address)
        {
            return await _userDao.UpdateAddress(userId, address);
        }

        public async Task<User> GetUserById(int id)
        {
            try
            {
                var user = await _userDao.GetById(id);
                if (user != null)
                {
                    user.PasswordHash = DecodeBase64(user.PasswordHash);
                }

                return user;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public string HashPassword(string password)
        {
            return Convert.ToBase64String(Encoding.UTF8.GetBytes(password));
        }

        public string DecodeBase64(string encodedData)
        {
            try
            {
                byte[] decodedBytes = Convert.FromBase64String(encodedData);
                return Encoding.UTF8.GetString(decodedBytes);
            }
            catch (FormatException ex)
            {
                throw new ArgumentException("Invalid Base64 string.", ex);
            }
        }

        public async Task<User> LoginByPhoneAndPassword(string phonenumber, string password)
        {
            try
            {
                var user = await _userDao.GetByPhoneNumber(phonenumber);
                if (user != null)
                {
                    string decodepass = DecodeBase64(user.PasswordHash);
                    if (decodepass == password)
                    {
                        return user;
                    }
                }

                return null;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<int> CountUser()
        {
            return await _userDao.Count();
        }

        private bool IsValidPassword(string password)
        {
            if (string.IsNullOrWhiteSpace(password) || password.Length < 6)
            {
                return false;
            }

            return true;
        }

        private bool IsValidFirstName(string firstName)
        {
            if (string.IsNullOrWhiteSpace(firstName) || firstName.Length > 100)
            {
                return false;
            }

            return true;
        }

        private bool IsValidLastName(string lastName)
        {
            if (string.IsNullOrWhiteSpace(lastName) || lastName.Length > 100)
            {
                return false;
            }

            return true;
        }

        public async Task<User> AuthenticateUser(string email, string password)
        {
            try
            {
                var user = await _userDao.GetByEmail(email);
                if (user != null)
                {
                    if (HashPassword(password).Equals(user.PasswordHash))
                    {
                        return user;
                    }

                    throw new Exception("Password not true");
                }

                throw new Exception("Email not found");
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }
    }
}