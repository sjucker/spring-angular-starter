package ch.stefanjucker.security;

import ch.stefanjucker.configuration.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Optional;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class JwtService {

    private final SignatureAlgorithm signatureAlgorithm = HS256;
    private final SecretKeySpec secretKey;

    public JwtService(AppProperties properties) {
        this.secretKey = new SecretKeySpec(properties.getJwtSecret().getBytes(UTF_8), signatureAlgorithm.getJcaName());
    }

    public String createJwt(String email) {
        Claims claims = Jwts.claims().setSubject(email);

        return Jwts.builder()
                   .setClaims(claims)
                   .signWith(secretKey, signatureAlgorithm)
                   .compact();
    }

    public Optional<Claims> validateJwt(String jwt) {
        var jwtParser = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build();

        try {
            return Optional.of(jwtParser.parseClaimsJws(jwt).getBody());
        } catch (JwtException ex) {
            log.error("unable to parse given JWT: " + jwt, ex);
            return Optional.empty();
        }
    }

}
