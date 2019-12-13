package pl.edu.wat.bookthevisit.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static pl.edu.wat.bookthevisit.config.SecurityConstants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {

        String header = servletRequest.getHeader(TOKEN_PREFIX);
//
//        System.out.println(header);
//        if (header == null || !header.startsWith("Bearer ")) {
//            throw new ServletException("Missing or invalid Authorization header");
//        } else {
//            try {
//                String token = header.substring(7);
//                System.out.println(token);
//
//                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//                System.out.println(claims);
//                servletRequest.setAttribute("claims", claims);
//            } catch (final SignatureException e) {
//                throw new ServletException("Invalid token");
//            }
//        }

        if (header == null || !header.startsWith(TOKEN_PREFIX))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(servletRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_TEXT);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRETKEY.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
