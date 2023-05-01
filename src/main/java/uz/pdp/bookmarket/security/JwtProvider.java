package uz.pdp.bookmarket.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.bookmarket.entity.Role;
import uz.pdp.bookmarket.entity.User;
import uz.pdp.bookmarket.repository.UserRepository;
import uz.pdp.bookmarket.service.AuthService;


import java.util.Date;


@Component
public class JwtProvider {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    private final long  expireTime= 1000*60*60*24;

    private final String secretKey = "maxfiysuzhechkimbilmasin";



    public String generateToken(String username, Role role){
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", role.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public User getUsernameFromToken(String token){
        User user = null;
        if (validationToken(token)) {
            String username = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
             user = (User) authService.loadUserByUsername(username);
        }else {
            return null;
        }
        return user;


    }
    public boolean validationToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            System.err.println("Muddati o'tgan");
        }catch (MalformedJwtException malformedJwtException){
            System.err.println("Buzilgan token");
        }catch (SignatureException signatureException){
            System.err.println("Kalit suz xato");
        }catch (UnsupportedJwtException unsupportedJwtException){
            System.err.println("Qo'llanilmaydigan token");
        }catch (IllegalArgumentException illegalArgumentException){
            System.err.println(" Bo'sh token");
        }
        return false;
    }
}
