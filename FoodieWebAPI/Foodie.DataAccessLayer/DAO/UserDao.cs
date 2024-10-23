using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;

namespace Foodie.DataAccessLayer.DAO
{
    public class UserDao 
    {
        private readonly FOODIEContext _context;

        public UserDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<User> Create(User entity)
        {
            await _context.Users.AddAsync(entity);
            await _context.SaveChangesAsync();
            return entity;
        }

        public async Task<bool> Delete(User entity)
        {
            _context.Users.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.UserId == id);
            if (user != null)
            {
                _context.Users.Remove(user);
               return await _context.SaveChangesAsync() >0;
            }
            return false;
        }

        public async Task<IEnumerable<User>> GetAll()
        {
            var users = await _context.Users
                    .Include(u => u.Role)
                    .ToListAsync();
            return users;
        }

        public async Task<User> GetById(int id)
        {
            var user = await _context.Users
                    .Include(u => u.Role)
                    .Include(u => u.UserLocations)
                    .FirstOrDefaultAsync(u => u.UserId == id);
            return user;
        }

        public async Task<bool> Update(User entity)
        {
            _context.Users.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task <IEnumerable<Role>> GetRoles()
        {
            return await _context.Roles.ToListAsync();

        }
        public async Task<User> GetByPhoneNumber(string phone)
        {
            var user = await _context.Users
                    .Include(u => u.Role)
                    .FirstOrDefaultAsync(u => u.PhoneNumber == phone);
            return user;
        }

        public async Task<User> GetByEmail(string email)
        {
            var user = await _context.Users
                    .Include(u => u.Role)
                    .Include(u=>u.Restaurants)
                    .FirstOrDefaultAsync(u => u.Email == email);
            return user;
        }
        public async Task<int> Count()
        {
            return await _context.Users.CountAsync();
        }

        public async Task<List<User>> GetUsers(int pageNumber, int pageSize)
        {
            var users = await _context.Users
                .Include(u => u.Role)
                .Skip((pageNumber-1)*pageSize).Take(pageSize)
                .ToListAsync();
            return users;
        }
        
    }
}
