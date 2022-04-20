package com.example.demo.controller;
;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.Sign;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.AuthService;
import com.example.demo.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final
    AuthenticationManager authenticationManager;

    final
    AuthService authService;

    final
    JwtProvider jwtProvider;

    final SignUpService signUpService;

//    final RoleRepository roleRepository;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = authService.loadUserByUsername(loginDTO.getUserName());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getUserName(), loginDTO.getPassword()));

            String token = jwtProvider.generateToken(loginDTO.getUserName());
            return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signUp")
    public HttpEntity<?> signUp(@RequestBody Sign sign){
        ApiResponse response = signUpService.signUp(sign);
        if (response.isSuccess()){
       String token = jwtProvider.generateToken(sign.getUserName());
       return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }

}
