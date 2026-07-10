package com.yujun.mtt.util;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.*;
import java.util.Date;

public class JwtHelper {
    // token过期时间 24小时
    private static long tokenExpiration = 24*60*60*1000;
    // token签名密钥
    private static String tokenSignKey = "123456";

    //生成token字符串，根据用户 id 创建一个 Token
    public static String createToken(Long userId) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userId
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }

    //判断token是否有效
    public static boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}
/**
 * 使用：
 * 1. 生成token：createToken(Long userId)
 * 2. 从token中获取userId：getUserId(String token)
 * 3. 判断token是否有效：isExpiration(String token)
 */