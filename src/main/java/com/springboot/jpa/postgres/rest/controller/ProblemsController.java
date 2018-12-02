package com.springboot.jpa.postgres.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.jpa.postgres.rest.exception.ResourceNotFoundException;
import com.springboot.jpa.postgres.rest.model.Problem;
import com.springboot.jpa.postgres.rest.repository.ProblemsRepository;

import javax.validation.Valid;

@RestController
public class ProblemsController {

    @Autowired
    private ProblemsRepository problemsRepository;

    @GetMapping("/problems")
    public Page<Problem> getProblems(Pageable pageable) {
        return problemsRepository.findAll(pageable);
    }


    @PostMapping("/problems")
    public Problem createProblem(@Valid @RequestBody Problem problem) {
        return problemsRepository.save(problem);
    }

    @PutMapping("/problems/{problemId}")
    public Problem updateProblem(@PathVariable Long problemId,
                                   @Valid @RequestBody Problem problemRequest) {
        return problemsRepository.findById(problemId)
                .map(problem -> {
                	problem.setTitle(problemRequest.getTitle());
                	problem.setDescription(problemRequest.getDescription());
                    return problemsRepository.save(problem);
                }).orElseThrow(() -> new ResourceNotFoundException("Problem not found with id " + problemId));
    }


    @DeleteMapping("/problems/{problemId}")
    public ResponseEntity<?> deleteproblem(@PathVariable Long problemId) {
        return problemsRepository.findById(problemId)
                .map(problem -> {
                    problemsRepository.delete(problem);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Problem not found with id " + problemId));
    }
}
