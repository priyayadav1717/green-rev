package com.greenrev.greenrev.repository;

import com.greenrev.greenrev.entity.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {
    List<UserRelation> findByUserId(String userId);
    List<UserRelation> findByFamilyMemberId(String familyMemberId);
    Optional<UserRelation> findByUserIdAndFamilyMemberId(String userId, String familyMemberId);
}