namespace Foodie.ManagementAPI.ResponseDto
{
    public class ViewPage<T>
    {
        public ViewPage(int pageNumber, int pageSize, IEnumerable<T> items, int totalItems)
        {
            CurrentPage = pageNumber;
            PageSize = pageSize;
            Items = items;
            TotalItems = totalItems;
            if (TotalItems % pageSize != 0) TotalPages = TotalItems / pageSize + 1;
            else TotalPages = TotalItems / pageSize;
            NextPage = CurrentPage >= TotalPages ? TotalPages : CurrentPage + 1;
            PreviousPage = CurrentPage <= 1 ? 1 : CurrentPage - 1;
        }

        public int CurrentPage { get; set; }
        public int PageSize { get; set; }
        public IEnumerable<T> Items { get; set; }
        public int TotalItems { get; set; }
        public int TotalPages { get; set; }
        public int NextPage { get; set; }
        public int PreviousPage { get; set; }
    }
}