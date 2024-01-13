package com.example.springdemo.config;

import com.example.springdemo.jwt.JWTAuthenticationFilter;
import com.example.springdemo.jwt.JwtService;
import com.example.springdemo.service.impl.RoleUserDetailsService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 表示这是一个配置类
@EnableWebSecurity // 启用Spring Security的Web安全支持
@RequiredArgsConstructor
public class SpringSecurityConfig {
    @Resource
    private JwtService jwtService;

    @Resource
    private RoleUserDetailsService roleUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(roleUserDetailsService) // 设置自定义的UserDetailsService
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/UserController/loginUser").permitAll() // 允许所有用户访问登录端点
                        .requestMatchers("/UserController/**").hasAuthority("ROLE_Client") // 特定路径需要Client权限
                        .requestMatchers("/UserController/addUser").permitAll()
                        .requestMatchers("/BusinessController/updateBusiness", "/BusinessController/deleteBusinessById").hasAuthority("ROLE_Admin")
                        .anyRequest().permitAll() // 所有其他请求都需要认证
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加JWT过滤器

        return http.build();
    }
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter(jwtService);
    }
}
