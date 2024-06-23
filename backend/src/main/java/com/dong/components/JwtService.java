package com.dong.components;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtService {

    public static final String SECRET_KEY = "THIS_IS_A_SECRET_KEY_12345678900000000";
    public static final byte[] SHARED_SECRET_KEY = SECRET_KEY.getBytes();
    public static final int EXPIRE_TIME = 60 * 60 * 1000;

    public String generateTokenLogin(String username) {
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET_KEY);

            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("username", username);
            builder.expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME));

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            System.err.println("Lỗi tạo token: " + e.getMessage());
        }
        return null;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET_KEY);
            if (signedJWT.verify(verifier)) {
                return signedJWT.getJWTClaimsSet();
            }
        } catch (JOSEException | ParseException e) {
            System.err.println("Lỗi phân tích/xác minh token: " + e.getMessage());
        }
        return null;
    }

    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        if (claims != null) {
            return claims.getExpirationTime();
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        if (claims != null) {
            try {
                return claims.getStringClaim("username");
            } catch (ParseException e) {
                System.err.println("Lỗi lấy tên người dùng từ token: " + e.getMessage());
            }
        }
        return null;
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration != null && expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        String username = getUsernameFromToken(token);
        return username != null && !username.isEmpty() && !isTokenExpired(token);
    }
}
