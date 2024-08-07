package com.timmy.TimmyRoom.repository;

import com.timmy.TimmyRoom.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    List<File> findAllByUserEmail(String email);
}
