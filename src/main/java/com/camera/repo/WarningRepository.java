package com.camera.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camera.entity.WarningEntity;

@Repository
public interface WarningRepository extends JpaRepository<WarningEntity, String>{
	public List<WarningEntity> findAll();
}


