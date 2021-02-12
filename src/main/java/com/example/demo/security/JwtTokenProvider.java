package com.example.demo.security;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.user_role;
import com.example.demo.exception.InvalidJwtAuthenticationException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.service.user_roleService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10L; // 10 days

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private user_roleService user_roleService;
    @Autowired
    private UserService UserService;

    @PostConstruct
    protected void init() {secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());}

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(new Date())//
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }
    @Transactional
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        User user = UserService.findByLogin(getUsername(token));
        List<user_role> user_roles  ;
        user_roles = user_roleService.findbyUserId(user.getId());
        List<String>roles = new ArrayList<String>();
        for (user_role role : user_roles)
        {
            roles.add(role.getRole().getNomRole());
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role_name : roles) {
            authorities.add(new SimpleGrantedAuthority(role_name));
        }


        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public String resolveDeviceToken(HttpServletRequest req) {
        return req.getHeader("RefreshDeviceToken");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException();
        }
    }
}
