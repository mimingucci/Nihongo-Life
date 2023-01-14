package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Map<Integer, String> solutions=new HashMap<>();
    @Column(name = "content", nullable = false, unique = true, length = 255)
    private String content;
    @Column(name = "correct_solution", nullable = false)
    private Integer correctSolution;

    public Question(String content, Map<Integer, String> solutions,Integer correctSolution) {
        this.solutions = solutions;
        this.content=content;
        this.correctSolution=correctSolution;
    }
}
