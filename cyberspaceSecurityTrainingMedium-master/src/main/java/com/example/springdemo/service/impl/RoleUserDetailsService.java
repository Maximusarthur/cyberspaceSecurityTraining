package com.example.springdemo.service.impl;

import com.example.springdemo.entities.User;
import com.example.springdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class RoleUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 假设有一个方法根据 roleId 获取角色名称
        String roleName = getRoleNameByRoleId(user.getRoleId());

        // 创建并返回一个Spring Security UserDetails对象
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                // 设置用户的角色
                .authorities(new SimpleGrantedAuthority("ROLE_" + roleName))
                .build();
    }

    private String getRoleNameByRoleId(Integer roleId) {
        // 实现该方法以根据 roleId 获取角色名称
        return switch (roleId) {
            case 1 -> "Admin";
            case 2 -> "Client";
            default -> "Guest";
        };
    }
}
