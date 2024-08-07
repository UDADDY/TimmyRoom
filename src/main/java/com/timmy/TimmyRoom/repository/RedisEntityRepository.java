package com.timmy.TimmyRoom.repository;

import com.timmy.TimmyRoom.entity.RedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisEntityRepository extends CrudRepository<RedisEntity, Long> {

    Optional<RedisEntity> findById(Long id);
}
