package kr.hs.dgsw.bubblechat.apiServer.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;

public class JwtUtils {

    public static String getEmailByJwt(String token) {
        String secret = "dnrhddltks0803_eornalfowoeks_thdtkdgns";
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

}
