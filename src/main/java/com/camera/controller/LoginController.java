package com.camera.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.camera.dto.LoginRequestDTO;
import com.camera.dto.SignUpRequestDTO;
import com.camera.entity.RefreshToken;
import com.camera.entity.UserEntity;
import com.camera.modelAPI.AuthenticationResult;
import com.camera.repo.UserRepository;
import com.camera.request.TokenRefreshRequest;
import com.camera.response.JwtResponse;
import com.camera.response.TokenRefreshResponse;
import com.camera.security.jwt.JwtUtils;
import com.camera.security.service.RefreshTokenService;
import com.camera.security.service.UserDetailsImpl;
import com.camera.service.TokenRefreshException;

@Controller
public class LoginController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RefreshTokenService refreshTokenService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
		try {
			// Create new user's account
			UserEntity userEntity = new UserEntity();
			userEntity.setName(signUpRequestDTO.getName());
			userEntity.setUserName(signUpRequestDTO.getUserName());
			userEntity.setEmail(signUpRequestDTO.getEmail());
			userEntity.setPassword(encoder.encode(signUpRequestDTO.getPassword()));

			userEntity = userRepository.save(userEntity);
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
		} catch (DataIntegrityViolationException ex) {
			// Duplicate email address
			return ResponseEntity.status(HttpStatus.CONFLICT).body("409 conflict: User address already exists");
		} catch (Exception ex) {
			// Other errors
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("500 Internal Server Error: Error occurred while registering user");
		}
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		try {

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequestDTO.getUserName(), loginRequestDTO.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String jwt = jwtUtils.generateJwtToken(userDetails);

			RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
			AuthenticationResult authenticationResult = new AuthenticationResult(jwt, jwt, refreshToken.getToken(),
					jwtUtils.getJwtExpirationMs());
			JwtResponse jwtResponse = new JwtResponse(authenticationResult);
			return ResponseEntity.ok(jwtResponse);
		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		} 
	}

	@PostMapping("/sign-out")
	public ResponseEntity<?> logoutUser() {
		System.out.println("Authenticatino: " + SecurityContextHolder.getContext().getAuthentication());
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Long userId = userDetails.getId();
		refreshTokenService.deleteByUserId(userId);

		return ResponseEntity.ok("Log out successful!");
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();

		return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser).map(user -> {
					String token = jwtUtils.generateTokenFromUserName(user.getUserName());
					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
	}
}
