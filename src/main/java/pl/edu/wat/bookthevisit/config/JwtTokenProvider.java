package pl.edu.wat.bookthevisit.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

//@Component
public class JwtTokenProvider
{
//    private static final long validityInMilliseconds = 1 * 60 * 60 * 1000;
//
//    public String generate(String username, String sign)
//    {
//        long currentTimeMillis = System.currentTimeMillis();
//        String token = Jwts
//                        .builder()
//                        .setSubject(username)
////                        .setClaims(claims)
//                        .claim("role", "user")
//                        .setIssuedAt(new Date(currentTimeMillis))
//                        .setExpiration(new Date(currentTimeMillis + validityInMilliseconds))   //10 minutes
//                        .signWith(SignatureAlgorithm.HS512, sign)
//                        .compact();
//        //
//        System.out.println("!!! Generate " + token);
//
//        return "Bearer " + token;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return false;
//    }
//
////    public Authentication getAuthentication(String token) {
////
////        Claims claims = Jwts.parser().setSigningKey("customLogin").parseClaimsJws(token).getBody();
////
////        Collection<? extends GrantedAuthority> authorities = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
////                .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
////
////        User principal = new User(claims.getSubject(), "", authorities);
////
////        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
////    }
////
////    public boolean validateToken(String authToken) {
////
////        try {
////            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(authToken);
////            return true;
////        } catch (SignatureException e) {
////            LOGGER.info("Invalid JWT signature: " + e.getMessage());
////            LOGGER.debug("Exception " + e.getMessage(), e);
////            return false;
////        }
//
////    //retrieve username from jwt token
////    public String getUsernameFromToken(String token) {
////        return getClaimFromToken(token, Claims::getSubject);
////    }
////    //retrieve expiration date from jwt token
////    public Date getExpirationDateFromToken(String token) {
////        return getClaimFromToken(token, Claims::getExpiration);
////    }
////    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = getAllClaimsFromToken(token);
////        return claimsResolver.apply(claims);
////    }
////    //for retrieveing any information from token we will need the secret key
////    private Claims getAllClaimsFromToken(String token) {
////        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
////    }
////    //check if the token has expired
////    private Boolean isTokenExpired(String token) {
////        final Date expiration = getExpirationDateFromToken(token);
////        return expiration.before(new Date());
////    }
////    //generate token for user
////    public String generateToken(UserDetails userDetails) {
////        Map<String, Object> claims = new HashMap<>();
////        return doGenerateToken(claims, userDetails.getUsername());
////    }
////    //while creating the token -
////    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
////    //2. Sign the JWT using the HS512 algorithm and secret key.
////    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
////    //   compaction of the JWT to a URL-safe string
////    private String doGenerateToken(Map<String, Object> claims, String subject) {
////        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
////                .signWith(SignatureAlgorithm.HS512, secret).compact();
////    }
////    //validate token
////    public Boolean validateToken(String token, UserDetails userDetails) {
////        final String username = getUsernameFromToken(token);
////        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
////    }
}

