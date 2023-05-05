package com.camera.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.camera.common.APIResponse;
import com.camera.dto.LoginRequestDTO;
import com.camera.dto.SignUpRequestDTO;
import com.camera.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;



	@PostMapping("/sign-up")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

		APIResponse apiResponse = loginService.signUp(signUpRequestDTO);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	

	@PostMapping("/sign-in")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = loginService.login(loginRequestDTO);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	/*
	 * Tạo một endpoint cho API sign-out với method POST. Xóa JWT khỏi cookie hoặc
	 * session của client. Trả về thông tin đăng xuất thành công cho client.
	 */
	@PostMapping("/sign-out")
	public ResponseEntity<APIResponse> logout(HttpServletRequest request, HttpServletResponse response) {
		// xóa JWT khỏi cookie hoặc session của client
		Cookie jwtCookie = new Cookie("jwtToken", null);
		jwtCookie.setMaxAge(0);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(true);
		jwtCookie.setPath("/");
		response.addCookie(jwtCookie);

		// trả về thông tin đăng xuất thành công cho client
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setData("UserEntity logged out successfully");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUser)
//                .map(user -> {
//                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//                        "Refresh token is not in database!"));
//    }
	
//    @PostMapping("/refresh-token")
//    public ResponseEntity<APIResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
//        // kiểm tra JWT có hợp lệ không
//        String jwt = JwtUtils.getJwtFromRequest(request);
//        if (!JwtUtils.validateJwtToken(jwt)) {
//            APIResponse apiResponse = new APIResponse();
//            apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//            apiResponse.setData("Invalid token");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
//        }
//
//        // lấy thông tin user từ JWT
//        String userName = JwtUtils.getUserNameFromJwtToken(jwt);
//        UserEntity userEntity = userRepository.findOneByUserNameIgnoreCase(userName);
//
//        // tạo ra một token mới và cập nhật vào cookie hoặc session của client
//        String newToken = jwtUtils.generateJwt(userEntity);
//        Cookie jwtCookie = new Cookie("jwtToken", newToken);
//        jwtCookie.setMaxAge(jwtUtils.getJwtExpirationMs());
//        jwtCookie.setHttpOnly(true);
//        jwtCookie.setSecure(true);
//        jwtCookie.setPath("/");
//        response.addCookie(jwtCookie);
//
//        // trả về token mới cho client
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setStatus(HttpStatus.OK.value());
//        Map<String , Object> data = new HashMap<>();
//        data.put("accessToken", newToken);
//        apiResponse.setData(data);
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//    }

}
