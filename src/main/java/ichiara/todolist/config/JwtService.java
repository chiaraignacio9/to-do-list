package ichiara.todolist.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String SECRET_KEY = "753778214125442A472D4A614E645267556B58703273357638792F423F452848";

    //Method to generate JWT token
    public String generateJwtToken(UserDetails userDetails){

        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // extract username from jwt token
    public String extractUsername(String token){
         return extractClaim(token, Claims::getSubject);
    }

    //extract all claims
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // extract one claim
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    // Method to encode the secret key
    private Key getSignInKey(){
        byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBites);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
