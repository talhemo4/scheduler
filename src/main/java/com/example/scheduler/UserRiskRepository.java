package com.example.scheduler;

import com.example.scheduler.io.entity.UserEntity;
import com.example.scheduler.io.entity.UserRiskEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRiskRepository extends PagingAndSortingRepository<UserRiskEntity, Long> {

  UserRiskEntity findByUserId(Long userId);
}
