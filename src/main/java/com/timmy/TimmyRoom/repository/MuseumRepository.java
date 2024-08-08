package com.timmy.TimmyRoom.repository;

import com.timmy.TimmyRoom.entity.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, String > {
}
