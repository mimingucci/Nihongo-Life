package com.nihongo.common.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<String> solutions=new ArrayList<>();
    @Column(name = "content", nullable = false, unique = true, length = 255)
    private String content;
    @Column(name = "correct_solution", nullable = false)
    private String correctSolution;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question(String content, List<String> solutions,String correctSolution) {
        this.solutions = solutions;
        this.content=content;
        this.correctSolution=correctSolution;
    }

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", solutions=" + solutions +
                ", content='" + content + '\'' +
                ", correctSolution='" + correctSolution + '\'' +
                '}';
    }
}
