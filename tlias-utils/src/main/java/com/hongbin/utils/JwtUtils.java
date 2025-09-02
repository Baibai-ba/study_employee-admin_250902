package com.hongbin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "hongbin";
    private static Long expire = 3600L * 1000 * 24;


    /**
     * 生成Jwt令牌
     * @param claims
     * @return
     */
    public static String generateJwt (Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析 Jwt 令牌
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}
