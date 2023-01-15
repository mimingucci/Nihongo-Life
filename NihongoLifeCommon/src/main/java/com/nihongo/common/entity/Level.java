package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "levels")
@Data
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "level",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons=new HashSet<>();

    @OneToMany(mappedBy = "level", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Alphabet> alphabets=new HashSet<>();
}
