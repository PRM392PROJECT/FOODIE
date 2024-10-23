namespace Foodie.ManagementAPI.ResponseDto
{
    public class Token
    {
        public string TokenString { get; set; }
        public DateTime Expiration { get; set; }

        public Token(string tokenString, DateTime expiration)
        {
            TokenString = tokenString;
            Expiration = expiration;
        }
    }
}
