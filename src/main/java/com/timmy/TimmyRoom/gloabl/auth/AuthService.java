package com.timmy.TimmyRoom.gloabl.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.TimmyRoom.dto.request.LoginRequestDTO;
import com.timmy.TimmyRoom.dto.request.SignupRequestDTO;
import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.excpetion.UserAlreadyExistsException;
import com.timmy.TimmyRoom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String login(LoginRequestDTO dto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtil.createAccessToken(authentication);
        return accessToken;
    }

    @Transactional
    public User signup(SignupRequestDTO request) {
        if(userRepository.existsById(request.getEmail()))
            throw new UserAlreadyExistsException();

        User user = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .role("ROLE_USER")
                .build();

        return userRepository.save(user);
    }
}
