package com.timmy.TimmyRoom.repository;

import com.timmy.TimmyRoom.entity.Museum;
import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.entity.UserMuseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMuseumRepository extends JpaRepository<UserMuseum, Long> {
    List<UserMuseum> findByUser(User user);
    List<UserMuseum> findByMuseum(Museum museum);
}
