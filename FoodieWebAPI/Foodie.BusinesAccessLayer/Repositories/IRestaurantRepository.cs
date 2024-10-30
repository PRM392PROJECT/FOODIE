using Foodie.DataAccessLayer.Models;


namespace Foodie.BusinesAccessLayer.Repositories
{
    public interface IRestaurantRepository
    {
        Task<Restaurant> Create(Restaurant restaurant);

        Task<bool> Delete(Restaurant restaurant);

        Task<bool> DeleteById(int id);

        Task<IEnumerable<Restaurant>> GetRestaurants(int pageNumber, int pageSize);

        Task<Restaurant> GetById(int id);

        Task<bool> Update(int restaurantId, Restaurant restaurant);

        Task<int> Count();
    }
}