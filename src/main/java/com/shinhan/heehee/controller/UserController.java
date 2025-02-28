package com.shinhan.heehee.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.heehee.config.AuthenticationFailure;
import com.shinhan.heehee.config.AuthenticationSuccess;
import com.shinhan.heehee.dto.request.BanUserDTO;
import com.shinhan.heehee.dto.response.UserDTO;
import com.shinhan.heehee.exception.BanUserException;
import com.shinhan.heehee.exception.UserNotFoundException;
import com.shinhan.heehee.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	AuthenticationSuccess success;

	@Autowired
	AuthenticationFailure failure;

	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<?> singUp(UserDTO userDto, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + userDto);
		return userService.signup(userDto);
	}

	@PostMapping("/login-processing")
	public void login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IOException, ServletException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");

		UserDTO user = userService.login(userId, userPw);
		BanUserDTO banUserDto = userService.banCheck(userId);
		
		// 회원 상태 확인
		if(banUserDto != null) {
			throw new BanUserException("정지된 사용자입니다.");
		}
		
		if (user == null) throw new UserNotFoundException();

		try {
			// 사용자 인증을 위한 UsernamePasswordAuthenticationToken 객체 생성
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, userPw);
			// AuthenticationManager를 사용하여 인증 수행
			Authentication authentication = authenticationManager.authenticate(token);
			// 인증 성공 후 SecurityContext에 인증 객체 설정
			SecurityContextHolder.getContext().setAuthentication(authentication);

			success.onAuthenticationSuccess(request, response, authentication);

		} catch (Exception e) {
			System.err.println(e);
			failure.onAuthenticationFailure(request, response, null);
			redirectAttributes.addAttribute("error", true);
		}
	}
	
	/*
	 * @GetMapping("/logout") public ResponseEntity<?> logout(HttpServletResponse
	 * response) { Cookie tokenCookie = new Cookie("Authorization", null);
	 * 
	 * tokenCookie.setMaxAge(0); tokenCookie.setPath("/");
	 * response.addCookie(tokenCookie);
	 * 
	 * return ResponseEntity.ok("로그아웃에 성공했습니다."); }
	 */
	
	@GetMapping("/loginCheck")
	public String logCheck() {
		return "/common/loginCheck";
	}
	
	@GetMapping("/dupIdCheck")
	@ResponseBody
	public Map<String,Object> dupIdCheck(@RequestParam String id) {
		return userService.dupIdCheck(id);
	}
	
	@GetMapping("/dupNickCheck")
	@ResponseBody
	public Map<String,Object> dupNickCheck(@RequestParam String nickName) {
		return userService.dupNickCheck(nickName);
	}
	
	@GetMapping("/dupEmailCheck")
	@ResponseBody
	public Map<String,Object> dupEmailCheck(@RequestParam String email) {
		return userService.dupEmailCheck(email);
	}
}
