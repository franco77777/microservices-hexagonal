package com.pragma.users.infrastructure.security;

import com.pragma.users.infrastructure.exception.InfrastructureException;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final IUserRepository userRepository;
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String generateToken(UserEntity userDetails) {
        return Jwts
                .builder()
                .setId(userDetails.getId().toString())
                .setIssuer(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new)[0])
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public TokenDto validate(String token) {
        if (isTokenExpired(token)) throw new InfrastructureException("Token Expired", HttpStatus.UNAUTHORIZED);
        String email = extractUserEmail(token);
        String role = extractUserRole(token);
        String userId = extractUserId(token);
        Optional < UserEntity > userDatabase = userRepository.findByEmail(email);
        if (userDatabase.isEmpty()) {
            throw new InfrastructureException("Token: email " + email + " not found", HttpStatus.NOT_FOUND);
        }
        if (!userDatabase.get().getRoles().toString().equals(role)){
            throw new InfrastructureException("Token: role is wrong", HttpStatus.NOT_FOUND);
        }
        if (!userDatabase.get().getId().toString().equals(userId)){
            throw new InfrastructureException("Token: userId is wrong", HttpStatus.NOT_FOUND);
        }
            return new TokenDto(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractUserRole(String token) {
        return extractClaim(token, Claims::getIssuer);
    }
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getId);
    }
}

