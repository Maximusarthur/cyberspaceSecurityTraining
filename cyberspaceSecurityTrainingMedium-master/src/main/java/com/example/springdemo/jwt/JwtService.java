package com.example.springdemo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // 静态变量，用作JWT的密钥
    private static final String SECRET_KEY = "sl1G5kmgb534rONwe9JeMwQESIDmlf2w/Yqre/3SVxY=";

    // 从JWT提取用户名
    public String extractUserNmae(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // 根据token和Claims的解析函数来提取特定的Claims信息
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 生成JWT，此重载方法没有附加Claims
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    // 根据额外的Claims和用户详细信息生成JWT
    public String generateToken(
            Map<String, Object> extracClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extracClaims) // 设置额外的Claims
                .setSubject(userDetails.getUsername())// 设置JWT主体（通常是用户名）
                .setIssuedAt(new Date(System.currentTimeMillis()))// 设置JWT的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 设置JWT的过期时间
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)// 设置签名算法和密钥
                .compact();// 构建JWT并返回其字符串表示
    }

    // 验证Token是否有效
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserNmae(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpireed(token);
    }

    // 检查Token是否已过期
    public boolean isTokenExpireed(String token){
        return extractExpiration(token).before(new Date());
    }

    // 提取Token的过期时间
    private Date extractExpiration(String token){
       return extractClaims(token, Claims::getExpiration);
    }

    // 解析Token，提取所有Claims
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())// 设置签名密钥
                .build()
                .parseClaimsJws(token)// 解析JWT
                .getBody();// 获取Claims
    }

    // 获取签名密钥
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);// 解码密钥
        return Keys.hmacShaKeyFor(keyBytes);// 创建签名密钥
    }
}
