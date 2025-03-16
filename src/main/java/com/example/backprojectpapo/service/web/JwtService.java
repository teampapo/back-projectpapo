package com.example.backprojectpapo.service.web;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;
    private SecretKey signingKey;

    @PostConstruct
    public void init(){

        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Извлечение элек. почты пользователя из токена
     *
     * @param token токен
     * @return элек. почта
     */
    public String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }

    /**
     * Извлечение ID пользователя из токена
     *
     * @param token токен
     * @return ID пользователя
     */
    public Integer extractId(String token) {
        return extractClaim(token, claims -> claims.get("id", Integer.class));
    }

    /**
     * Генерация токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof CustomUserDetails customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("email", customUserDetails.getUsername());
            claims.put("role", customUserDetails.getRole());
            claims.put("createdDateTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        return generateToken(claims, userDetails);
    }

    /**
     * Проверка токена на валидность
     *
     * @param token       токен
     * @param userDetails данные пользователя
     * @return true, если токен валиден
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String userName = extractEmail(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Извлечение данных из токена
     *
     * @param token           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers) {

        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param userDetails данные пользователя
     * @return токен
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(),Jwts.SIG.HS256).compact();
    }

    /**
     * Проверка токена на просроченность
     *
     * @param token токен
     * @return true, если токен просрочен
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлечение даты истечения токена
     *
     * @param token токен
     * @return дата истечения
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    private SecretKey getSigningKey(){
        return signingKey;
    }
}
