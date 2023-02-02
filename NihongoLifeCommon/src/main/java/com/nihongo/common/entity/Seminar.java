package com.nihongo.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seminars")
public class Seminar extends Studying{
    @Column(name = "intro", length = 1000)
    private String intro;

    @OneToMany(mappedBy = "seminar", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<VideoLesson> lessons=new HashSet<>();

    public Seminar() {
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Set<VideoLesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<VideoLesson> lessons) {
        this.lessons = lessons;
    }
}
