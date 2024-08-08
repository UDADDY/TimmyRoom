package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.entity.Museum;
import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.entity.UserMuseum;
import com.timmy.TimmyRoom.repository.UserMuseumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserMuseumService {
    private final UserMuseumRepository userMuseumRepository;
    private final UserService userService;
    private final MuseumService museumService;

    @Transactional
    public UserMuseum likeMuseum(String email, Long museumId){
        User user = userService.findUserByEmail(email);
        Museum museum = museumService.findById(museumId);

        UserMuseum userMuseum = UserMuseum.builder()
                .user(user)
                .museum(museum)
                .build();

        return userMuseumRepository.save(userMuseum);
    }

    @Transactional
    public List<Museum> getMuseumByEmail(String email) {
        User user = userService.findUserByEmail(email);
        List<UserMuseum> userMuseums = userMuseumRepository.findByUser(user);

        return userMuseums.stream().map(userMuseum -> userMuseum.getMuseum()).toList();
    }
}
