package com.drelang.smartlock.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
  *  生成 JwtToken 工具类
 *  Jwt token 的格式 header.payload.signature
 *  header 的格式 (算法, token 的类型) :
 *  {"alg": "HS512", "typ": "JWT"}
 *  payload 的格式 (用户名, 生效时间, 过期时间) :
 *  {"sub": "drelang", "created": 1578765467, "exp":  1589089378}
 *  signature 的生成算法 :
 *  HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload), secret)
  * Created by Drelang on 2019/01/10
  */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     *  根据负载生成 Jwt token
     * @param claims 用户名和创建时间
     * @return Jwt token 的字符串
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                            .setClaims(claims)
                            .setExpiration(generateExpirationDate())
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     *  从 jwt token 中获取负载
     * @param token jwt token
     * @return 生成 jwt token 时的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try{
            claims = Jwts.parser()
                                    .setSigningKey(secret)
                                    .parseClaimsJws(token)
                                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT 格式验证失败: { }", token);
        }
        return claims;
    }

    /**
     *  从 Jwt Token 中获取用户名
     * @param token  Jwt Token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject(); // 返回负载中 sub 字段值
        } catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     *  验证 token 的合法性
     * @param token  Jwt token
     * @param userDetails 用户详情
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    /**
     *  判断 token 是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }

    /**
     *  判断 token 是否可以刷新
     * @param token
     * @return
     */
    public Boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
