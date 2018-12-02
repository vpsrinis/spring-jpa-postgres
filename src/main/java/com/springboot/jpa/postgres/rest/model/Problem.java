package com.springboot.jpa.postgres.rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "problem")
public class Problem extends TraceModel {
    @Id
    @GeneratedValue(generator = "problem_generator")
    @SequenceGenerator(
            name = "problem_generator",
            sequenceName = "problem_sequence",
            initialValue = 100
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
