package com.timmy.TimmyRoom.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.TimmyRoom.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

//        CustomUserInfoDto dto = modelMapper.map(member, CustomUserInfoDto.class);
        MemberDto dto = objectMapper.convertValue(member, MemberDto.class);

        return new CustomUserDetails(dto);
    }
}
