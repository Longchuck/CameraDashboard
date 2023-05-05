package com.camera.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camera.entity.RefreshToken;
import com.camera.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findOneByEmailIgnoreCaseAndPassword(String email, String password);
    UserEntity findOneByUserName(String userName);
	Optional<UserEntity> findById(Long userId);
}
