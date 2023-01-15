package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.PERSIST)
    private Set<Question> questions=new HashSet<>();
    @Column(name = "score", nullable = false)
    private Integer score;
    @ManyToMany(mappedBy = "quiz")
    private Set<Student> students=new HashSet<>();


    public Quiz() {
    }

    public Quiz(String title, Set<Question> questions, Integer score){
        this.title = title;
        this.questions=questions;
        this.score=score;
    }
}

