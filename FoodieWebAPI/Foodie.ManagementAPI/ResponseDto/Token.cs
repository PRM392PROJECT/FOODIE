namespace Foodie.ManagementAPI.ResponseDto
{
    public class Token
    {
        public string TokenString { get; set; }
        public DateTime Expiration { get; set; }
        public UserResponse User { get; set; }

        public Token(string tokenString, DateTime expiration, UserResponse user)
        {
            TokenString = tokenString;
            Expiration = expiration;
            User = user;
        }
    }
}