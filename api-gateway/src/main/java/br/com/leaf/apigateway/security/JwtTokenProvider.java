package br.com.leaf.apigateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private String secretKey;
    private final UserDetailsService userDetailsService;
    private final DateTimeProvider dateTimeProvider;

    public JwtTokenProvider(final @Value("${jwt.secret}") String secretKey,
                            final UserDetailsService userDetailsService,
                            final DateTimeProvider dateTimeProvider) {
        this.secretKey = secretKey;
        this.userDetailsService = userDetailsService;
        this.dateTimeProvider = dateTimeProvider;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

//    public String createToken(User user, Client client ,List<String> roles) throws UnsupportedEncodingException {
//        final LocalDateTime localDateTime = this.dateTimeProvider.dateTime();
//        final Date now = this.dateTimeProvider.toDate(localDateTime);
//        return JWT.create().withSubject(user.getUsername())
//                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
//                .withIssuedAt(now)
//                .withClaim("clientName", client.getName())
//                .sign(Algorithm.HMAC256(secretKey));
//    }

    public String createToken() {
        final LocalDateTime localDateTime = this.dateTimeProvider.dateTime();
        final Date now = this.dateTimeProvider.toDate(localDateTime);
        return JWT.create().withSubject("rafael")
//                .withArrayClaim("roles", Arrays.asList("teste", "teste2").toArray())
                .withIssuedAt(now)
                .withClaim("clientName", "teste")
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication getAuthentication(String token) throws UnsupportedEncodingException {
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) throws UnsupportedEncodingException {
        final DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

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
        final JWTVerifier require = JWT.require(Algorithm.HMAC256(this.secretKey))
                .acceptExpiresAt(0)
                .build();
        require.verify(token);
        return JWT.decode(token);
    }

}
