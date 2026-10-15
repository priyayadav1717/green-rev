package com.greenrev.greenrev.repository;

import com.greenrev.greenrev.entity.HealthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {
    List<HealthLog> findByUserId(String userId);
}