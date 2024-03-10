package com.rrobinvip.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * Creates a JWT (JSON Web Token) using the specified secret key, TTL (Time To Live), and claims.
     *
     * @param secretKey The secret key used for signing the JWT. The key is used with the HS256 signature algorithm.
     * @param ttlMillis The time to live in milliseconds. This value is added to the current system time to set the expiration time of the token.
     * @param claims A map of claims to be included in the JWT. These can be both standard and custom claims. Custom claims must be set before any standard claim values to avoid overwriting them.
     * @return A compact URL-safe JWT string.
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // Signature algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // Set expire tine
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // Create JWT body
        JwtBuilder builder = Jwts.builder()
                // If there are private claims, you must first set these self-created private claims.
                // This is to assign values to the builder's claims.
                // Once written after the assignment of standard claims, it will overwrite those standard claims.
                .setClaims(claims)
                // Signature algo and secret key
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // Expire date
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Parses a given JWT (JSON Web Token) and extracts the claims contained within it.
     *
     * This method uses the specified secret key to validate the token's signature and ensure its integrity
     * and authenticity. If the token is valid, the method returns the claims contained in the token's payload.
     * These claims represent the token's data, including both standard and custom claims set by the issuer.
     *
     * @param secretKey The secret key used to sign the JWT. This must match the key used to sign the token
     *                  for the signature to be verified successfully.
     * @param token     The JWT string to be parsed and from which the claims are to be extracted.
     * @return Claims   An object representing the claims contained in the JWT. This includes registered claims
     *                  (like issuer, subject, and expiration time) as well as any custom claims added by the issuer.
     * @throws io.jsonwebtoken.JwtException if any problem occurs during parsing the token, such as if the token is
     *                                      invalid, expired, or tampered with.
     */
    public static Claims parseJWT(String secretKey, String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

}
