package com.example.springdemo.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements UserDetails {
	@TableId
	private Integer userId; // 用户ID
	private String passwd; // 用户密码
	private String userName; // 用户姓名
	private Integer userSex; // 用户性别
	private Integer delTag;
	private Integer roleId; // 用户的角色信息

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 假设您有一个方法来根据 roleId 获取角色名称
		String roleName = getRoleNameByRoleId(roleId);
		// 创建一个 GrantedAuthority 集合
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));

		return authorities;
	}

	private String getRoleNameByRoleId(Integer roleId) {
		// 实际实现应该查询数据库或使用其他方式根据 roleId 获取角色名称
        return switch (roleId) {
            case 1 -> "Admin";
            case 2 -> "Client";
            default -> "Guest";
        };
	}

	@Override
	public String getPassword() {
		return passwd;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
