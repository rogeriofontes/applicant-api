package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.util.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

@Slf4j
public class TokenAuthenticationService {

    public static  void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + JWTUtil.EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, JWTUtil.SECRET).compact();
        res.addHeader(JWTUtil.HEADER_STRING, JWTUtil.TOKEN_PREFIX + " " + JWT);
    }

    public static  Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWTUtil.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser().setSigningKey(JWTUtil.SECRET)
                    .parseClaimsJws(token.replace(JWTUtil.TOKEN_PREFIX, "")).getBody().getSubject();

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            else
                return null;
        }
        return null;
    }
}
