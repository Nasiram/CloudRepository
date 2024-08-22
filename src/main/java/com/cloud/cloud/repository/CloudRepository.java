package com.cloud.cloud.repository;

import com.cloud.cloud.GitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudRepository extends JpaRepository<GitInfo,Long> {
}
