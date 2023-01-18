package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seminars")
@Data
public class Seminar extends Studying{
    @Column(name = "intro", length = 1000)
    private String intro;

    @OneToMany(mappedBy = "seminar", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<VideoLesson> lessons=new HashSet<>();

    public Seminar() {
    }
}
