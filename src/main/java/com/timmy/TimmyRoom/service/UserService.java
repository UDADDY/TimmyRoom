package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.gloabl.error.exception.UserNotFoundException;
import com.timmy.TimmyRoom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findById(email).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }
}
