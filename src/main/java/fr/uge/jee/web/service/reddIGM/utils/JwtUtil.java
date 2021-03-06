package fr.uge.jee.web.service.reddIGM.utils;

import fr.uge.jee.web.service.reddIGM.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtil {

    private String SECRET_KEY = "remiForax";

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Générer un token
    public String generateToken(User user){
        User.Authority authority = user.getAuthority();
        return createToken(authority, user.getUsername(), user.getId());
    }

    // Création du token, claims = ce qu'on veut stocker à l'intérieur
    private String createToken(User.Authority authority, String subject, Long id){
        return Jwts.builder().claim("auth", authority).claim("id", id).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // Check si le token est valide
    //, User user
    public Boolean validateToken(String token){
        //final String userName = extractUserName(token);
        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return true;
    }

    public Authentication getAuthentication(String token) {

        Claims claims = extractAllClaims(token);
        User.Authority authority = (User.Authority.valueOf(claims.get("auth").toString()));
        Long userId = (Long.valueOf(claims.get("id").toString()));


        //auth pas bonnes ici
        User principal = new User(claims.getSubject(), null, null, authority, null, null, false);
        principal.setId(userId);
        return new UsernamePasswordAuthenticationToken(principal, token, null);
    }

}
