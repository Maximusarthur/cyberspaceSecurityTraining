package com.example.springdemo.controller;

import com.example.springdemo.entities.User;
import com.example.springdemo.jwt.AuthenticationResponse;
import com.example.springdemo.jwt.JwtService;
import com.example.springdemo.service.UserService;
import com.example.springdemo.service.impl.RoleUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserController")
public class UserController {

	@Resource
	private RoleUserDetailsService roleUserDetailsService;

	@Resource
	private UserService userService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Resource
	private JwtService jwtService;

	@Resource
	private PasswordEncoder passwordEncoder;

	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(String userName, String passwd) {
		try {
			// 使用 AuthenticationManager 进行认证
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userName, passwd)
			);
		} catch (BadCredentialsException e) {
			// 如果认证失败，返回适当的响应
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
		}
		// 如果认证成功，使用 UserDetails 加载用户信息
		UserDetails userDetails = roleUserDetailsService.loadUserByUsername(userName);
		// 生成 JWT
		String jwt = jwtService.generateToken(userDetails);

		// 返回 JWT
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/listUsers")
	public List<User> listUsers(User user) {
		return userService.listUsers(user);
	}

	@GetMapping("/getUserById")
	public User getUserById(@RequestParam(required = false) Integer userId) {
		return userService.getUserById(userId);
	}

	@PostMapping("/addUser")
	public int saveUser(@RequestBody User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPasswd(encryptedPassword);
		return userService.saveUser(user);
	}

	@PutMapping("/updateUser/{userId}")
	public int updateUser(@PathVariable Integer userId, @RequestBody User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPasswd(encryptedPassword);
		return userService.updateUser(userId, user);
	}


	@DeleteMapping("/removeUserById")
	public int removeUser(@RequestParam Integer userId) {
		return userService.removeUser(userId);
	}
}
