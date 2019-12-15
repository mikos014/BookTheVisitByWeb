package pl.edu.wat.bookthevisit.config;

public class SecurityConstants
{
    public static final String SECRETKEY = "A#bC1";
    public static final long EXPIRATION_TIME = 1 * 60 * 60 * 1000; //miliseconds
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_TEXT = "Authorization";
}
