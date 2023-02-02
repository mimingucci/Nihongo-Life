package com.nihongo.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
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

    public Quiz(String title, Integer score) {
        this.title = title;
        this.score = score;
    }

    public Quiz(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

