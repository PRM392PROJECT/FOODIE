using Foodie.DataAccessLayer.DAO;
using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;


namespace Foodie.BusinesAccessLayer.Repositories
{
    public class ProductFeedbackRepository : IProductFeedbackRepository
    {
        private readonly ProductFeedbackDao _productFeedbackDao;

        public ProductFeedbackRepository(FOODIEContext context)
        {
            _productFeedbackDao = new ProductFeedbackDao(context);
        }

        public async Task<int> CountByProductId(int productId)
        {
            return await _productFeedbackDao.CountByProductId(productId);
        }

        public async Task<ProductFeedback> CreateFeedback(ProductFeedback feedback)
        {
            try
            {
                feedback = await _productFeedbackDao.Create(feedback);
                return feedback;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<bool> DeleteFeedback(int productId)
        {
            try
            {
                return await _productFeedbackDao.Delete(productId);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<IEnumerable<ProductFeedback>> GetFeedback(int productId, int pageNumber, int pageSize)
        {
            try
            {
                var feds = await _productFeedbackDao.GetFeedBacks(productId, pageNumber, pageSize);
                return feds;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public async Task<ProductFeedback> GetFeedbackById(int id)
        {
            try
            {
                var fed = await _productFeedbackDao.GetById(id);
                return fed;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }
    }
}