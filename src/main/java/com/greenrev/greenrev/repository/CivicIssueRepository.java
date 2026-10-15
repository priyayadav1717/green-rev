package com.greenrev.greenrev.repository;

import com.greenrev.greenrev.entity.CivicIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CivicIssueRepository extends JpaRepository<CivicIssue, Long> {
    List<CivicIssue> findByCitizenId(String citizenId);
    List<CivicIssue> findByStatus(String status);
}