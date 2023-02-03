package com.nihongo.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "seminars")
public class Seminar extends Studying{
    @Column(name = "intro", length = 1000)
    private String intro;

    @OneToMany(mappedBy = "seminar", orphanRemoval = true)
    private Set<VideoLesson> lessons=new LinkedHashSet<>();

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
