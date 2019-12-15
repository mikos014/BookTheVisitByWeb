package pl.edu.wat.bookthevisit.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.entities.UserEntity;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static pl.edu.wat.bookthevisit.config.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try
        {
            UserEntity creds = new ObjectMapper()
                    .readValue(request.getInputStream(), UserEntity.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            Collections.emptyList())
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException
    {
        long currentTimeMillis = System.currentTimeMillis();

        String token = JWT.create()
                        .withSubject(((User) authResult.getPrincipal()).getUsername())
                        .withExpiresAt(new Date(currentTimeMillis + EXPIRATION_TIME))
                        .sign(Algorithm.HMAC512(SECRETKEY.getBytes()));
//                Jwts
//                        .builder()
//                        .setSubject(((UserDto) authResult.getPrincipal()).getEmail())
////                        .claim("role", "user")
//                        .setIssuedAt(new Date(currentTimeMillis))
//                        .setExpiration(new Date(currentTimeMillis + EXPIRATION_TIME))   //10 minutes
//                        .signWith(SignatureAlgorithm.HS512, SECRETKEY.getBytes())
//                        .compact();

//        response.addHeader(HEADER_TEXT, TOKEN_PREFIX + token);
        System.out.println(TOKEN_PREFIX + token);
        response.addCookie(new Cookie(HEADER_TEXT, TOKEN_PREFIX + token));
    }
}