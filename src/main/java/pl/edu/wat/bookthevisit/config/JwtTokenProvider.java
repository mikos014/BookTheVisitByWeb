package pl.edu.wat.bookthevisit.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider
{
    private static long validityInMilliseconds = 600000;

    public String generate(Map<String, Object> claims, String issuer, String sign)
    {
        long currentTimeMillis = System.currentTimeMillis();
        String token = Jwts
                        .builder()
                        .setClaims(claims)
                        .setIssuer(issuer)
                        .setIssuedAt(new Date(currentTimeMillis))
                        .setExpiration(new Date(currentTimeMillis + validityInMilliseconds))   //10 minutes
                        .signWith(SignatureAlgorithm.HS512, sign)
                        .compact();
        System.out.println("!!! Generate " + token);
        return "Bearer " + token;
    }

}
