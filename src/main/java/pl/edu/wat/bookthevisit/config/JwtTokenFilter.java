package pl.edu.wat.bookthevisit.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtTokenFilter implements javax.servlet.Filter
{
//    @Value("${security.oauth2.client.clientSecret}")
    private static final String secretKey = "1234";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String header = httpServletRequest.getHeader("authorization");

        System.out.println(header);
        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header");
        }
        else
        {
            try
            {
                String token = header.substring(7);
                System.out.println(token);

                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                System.out.println(claims);
                servletRequest.setAttribute("claims", claims);
            }
            catch (final SignatureException e) {
                throw new ServletException("Invalid token");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
