package com.timmy.TimmyRoom.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.TimmyRoom.dto.MemberDto;
import com.timmy.TimmyRoom.dto.LoginRequestDto;
import com.timmy.TimmyRoom.dto.SignupRequestDto;
import com.timmy.TimmyRoom.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Transactional
    public String login(LoginRequestDto dto){
        String email = dto.getEmail();
        String password = dto.getPassword();
        Member member = memberRepository.findMemberByEmail(email);

        if(member == null)
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");

        if(!passwordEncoder.matches(password, member.getPassword()))
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");

//        CustomUserInfoDto info = modelMapper.map(member, CustomUserInfoDto.class);
        MemberDto info = objectMapper.convertValue(member, MemberDto.class);

        String accessToken = jwtUtil.createAccessToken(info);
        return accessToken;
    }

    @Transactional
    public String  signup(SignupRequestDto request) {
        Member member = Member.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .role("ROLE_USER")
                .build();

        Member savedMember= memberRepository.save(member);
        MemberDto memberDto = objectMapper.convertValue(savedMember, MemberDto.class);

        String accessToken = jwtUtil.createAccessToken(memberDto);

        return accessToken;
    }
}
