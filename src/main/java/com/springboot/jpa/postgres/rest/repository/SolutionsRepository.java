package com.springboot.jpa.postgres.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.jpa.postgres.rest.model.Solution;

import java.util.List;

@Repository
public interface SolutionsRepository extends JpaRepository<Solution, Long> {
    List<Solution> findByProblemId(Long problemId);
}
