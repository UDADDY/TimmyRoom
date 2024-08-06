package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.excpetion.UserNotFoundException;
import com.timmy.TimmyRoom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findById(email).orElseThrow(() -> new UserNotFoundException());
    }
}
