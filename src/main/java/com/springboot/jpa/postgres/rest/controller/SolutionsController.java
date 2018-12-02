package com.springboot.jpa.postgres.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.jpa.postgres.rest.exception.ResourceNotFoundException;
import com.springboot.jpa.postgres.rest.model.Solution;
import com.springboot.jpa.postgres.rest.repository.SolutionsRepository;
import com.springboot.jpa.postgres.rest.repository.ProblemsRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SolutionsController {

    @Autowired
    private SolutionsRepository solutionsRepository;

    @Autowired
    private ProblemsRepository problemsRepository;

    @GetMapping("/problems/{problemId}/solutions")
    public List<Solution> getSolutionByProblemId(@PathVariable Long problemId) {
        return solutionsRepository.findByProblemId(problemId);
    }

    @PostMapping("/problems/{problemId}/solutions")
    public Solution addSolution(@PathVariable Long problemId,
                            @Valid @RequestBody Solution solution) {
        return problemsRepository.findById(problemId)
                .map(problem -> {
                    solution.setProblem(problem);
                    return solutionsRepository.save(solution);
                }).orElseThrow(() -> new ResourceNotFoundException("Problem not found with id " + problemId));
    }

    @PutMapping("/problems/{problemId}/solutions/{solutionId}")
    public Solution updateSolution(@PathVariable Long problemId,
                               @PathVariable Long solutionId,
                               @Valid @RequestBody Solution solutionRequest) {
        if(!problemsRepository.existsById(problemId)) {
            throw new ResourceNotFoundException("Problem not found with id " + problemId);
        }

        return solutionsRepository.findById(solutionId)
                .map(solution -> {
                    solution.setText(solutionRequest.getText());
                    return solutionsRepository.save(solution);
                }).orElseThrow(() -> new ResourceNotFoundException("Solution not found with id " + solutionId));
    }

    @DeleteMapping("/problems/{problemId}/solutions/{solutionId}")
    public ResponseEntity<?> deleteSolution(@PathVariable Long problemId,
                                          @PathVariable Long solutionId) {
        if(!problemsRepository.existsById(problemId)) {
            throw new ResourceNotFoundException("Problem not found with id " + problemId);
        }

        return solutionsRepository.findById(solutionId)
                .map(solution -> {
                    solutionsRepository.delete(solution);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Solution not found with id " + solutionId));

    }
}
