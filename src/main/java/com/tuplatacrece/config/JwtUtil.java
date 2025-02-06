package com.tuplatacrece.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final String SECRET_KEY = "secretoSuperSeguroMuyLargoParaHMACSHA256ySeguro"; // 64 caracteres = 512 bits


  public String generateToken(String dni) {
    return Jwts.builder()
        .subject(dni)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de expiración
        .signWith(getSignInKey())
        .compact();
  }

  private SecretKey getSignInKey() {
    byte[] bytes = Base64.getDecoder()
        .decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    return new SecretKeySpec(bytes, "HmacSHA256");
  }

  public String extractDni(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claimsResolver.apply(claims);
  }

  public boolean validateToken(String token, String dni) {
    return dni.equals(extractDni(token)) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}
