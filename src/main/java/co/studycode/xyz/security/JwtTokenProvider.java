package co.studycode.xyz.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateJwtToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(Long.toString(userPrinciple.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());

    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid Jwt Signature -> Messsage : {}",e);
        }catch (MalformedJwtException e){
            logger.error("Invalid Jwt Token -> Message: {}",e);
        }catch (ExpiredJwtException e){
            logger.error("Expired jwt token ->Message: {}",e);
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported Jwt token -> :{}",e);
        }catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty -> Message {}", e);
        }

        return false;
    }
}
