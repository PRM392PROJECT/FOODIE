using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;


namespace Foodie.BusinesAccessLayer.Repositories
{
    public class RestaurantRepository : IRestaurantRepository
    {
        private readonly RestaurantDao _restaurantDao;

        public RestaurantRepository(FOODIEContext context)
        {
            _restaurantDao = new RestaurantDao(context);
        }

        public async Task<Restaurant> Create(Restaurant restaurant)
        {
            try
            {
                return await _restaurantDao.Create(restaurant);
            }
            catch (Exception ex)
            {
                throw new Exception("Error creating restaurant: " + ex.Message);
            }
        }

        public async Task<bool> Delete(Restaurant restaurant)
        {
            try
            {
                return await _restaurantDao.Delete(restaurant);
            }
            catch (Exception ex)
            {
                throw new Exception("Error deleting restaurant: " + ex.Message);
            }
        }

        public async Task<bool> DeleteById(int id)
        {
            try
            {
                return await _restaurantDao.DeleteById(id);
            }
            catch (Exception ex)
            {
                throw new Exception("Error deleting restaurant by ID: " + ex.Message);
            }
        }

        public async Task<IEnumerable<Restaurant>> GetRestaurants(int pageNumber, int pageSize)
        {
            try
            {
                return await _restaurantDao.GetRestaurants(pageNumber,pageSize);
            }
            catch (Exception ex)
            {
                throw new Exception("Error retrieving all restaurants: " + ex.Message);
            }
        }

        public async Task<Restaurant> GetById(int id)
        {
            try
            {
                return await _restaurantDao.GetById(id);
            }
            catch (Exception ex)
            {
                throw new Exception("Error retrieving restaurant by ID: " + ex.Message);
            }
        }

        public async Task<bool> Update(int restaurantId,Restaurant restaurant)
        {
            try
            {
                var restaurantExit = await _restaurantDao.GetById(restaurantId);
                if (restaurantExit != null)
                {
                    restaurantExit.Name = restaurant.Name;
                    restaurantExit.RestaurantLocations = restaurant.RestaurantLocations;
                    restaurantExit.PhoneNumber = restaurant.PhoneNumber;
                    restaurantExit.UpDateAt = DateTime.Now;
                    restaurantExit.Status = restaurant.Status;
                    return await _restaurantDao.Update(restaurantExit);
                }
                return false;
            }
            catch (Exception ex)
            {
                throw new Exception("Error updating restaurant: " + ex.Message);
            }
        }
       
        public async Task<int> Count()
        {
            return await _restaurantDao.Count();
        }
    }
}
