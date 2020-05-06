package com.common.utils;

import io.jsonwebtoken.*;
import io.netty.handler.codec.base64.Base64Encoder;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.util.Base64Utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 * @author
 * @date 2020/5/5
 */
public class JwtUtil {
    /**
     * Token过期时间，30分钟
     */
    private static final Long EXPIRE_TIME =  1000 * 60 * 60 * 30L;

    /**
     * 密钥
     */
    private static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 生成token
     * @param obj
     * @param expireTime
     * @return
     */
    public static String createJwtToken(Object obj, Long expireTime) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.claim("info", obj);
        //token创建时间
        Long createTime = System.currentTimeMillis();
        jwtBuilder.setIssuedAt(new Date(createTime));
        //token过期时间
        jwtBuilder.setExpiration(new Date(createTime + expireTime));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, APP_SECRET);
        return jwtBuilder.compact();
    }
    public static String createJwtToken(Object obj) {
        return createJwtToken(obj, EXPIRE_TIME);
    }

    public static boolean checkToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token异常");
        }
        return true;
    }


    public static Object getTokenInfo(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(APP_SECRET);
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        Claims c =  jws.getBody();
        return c.get("info");
    }

}
