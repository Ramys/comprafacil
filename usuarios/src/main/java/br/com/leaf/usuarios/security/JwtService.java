package br.com.leaf.usuarios.security;

import br.com.leaf.usuarios.enums.PerfisEnum;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    private final UserDetailsService userDetailsService;
    private final DateTimeProvider dateTimeProvider;

    public boolean validateToken(String token) {
        try {
            getDecodedJWT(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            log.warn("Invalid token! Msg: {}", e.getMessage());
            return false;
        }
    }

    private DecodedJWT getDecodedJWT(String token) throws UnsupportedEncodingException {
        var require = JWT.require(Algorithm.HMAC256(SECRET))
                .acceptExpiresAt(0)
                .build();
        require.verify(token);
        return JWT.decode(token);
    }

    public String generateToken(String email, PerfisEnum perfil, Long id) {
        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", perfil);
//
//        if (perfil == PerfisEnum.CLIENTE) {
//            claims.put("cartId", UUID.randomUUID().toString());
//            claims.put("userId", id);
//        }

        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        final LocalDateTime localDateTime = this.dateTimeProvider.dateTime();
        final Date now = this.dateTimeProvider.toDate(localDateTime);
        return JWT.create()
                .withSubject(email)
//                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withIssuedAt(now)
//                .withClaim("clientName", client.getName())
                .sign(Algorithm.HMAC256(SECRET));
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token) throws UnsupportedEncodingException {
        final DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getSubject();
    }

    public Authentication getAuthentication(String token) throws UnsupportedEncodingException {
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
