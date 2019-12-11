package pl.edu.wat.bookthevisit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtToken
{
    public static String generate(Map<String, Object> claims, String issuer, String sign)
    {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 600000))   //10 minutes
                .signWith(SignatureAlgorithm.HS512, sign)
                .compact();
    }

}
