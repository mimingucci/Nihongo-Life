package com.nihongo.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    private Integer id;
    private String name;
    private Set<Question> questions=new HashSet<>();
    private Integer score;
}
