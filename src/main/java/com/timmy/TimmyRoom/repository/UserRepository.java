package com.timmy.TimmyRoom.repository;

import com.timmy.TimmyRoom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String > {

    User findMemberByEmail(String email);
}
