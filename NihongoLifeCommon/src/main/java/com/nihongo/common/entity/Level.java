package com.nihongo.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "level",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons=new HashSet<>();

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Alphabet> alphabets=new HashSet<>();

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY, orphanRemoval = true)
   private Set<Seminar> seminars=new HashSet<>();

    public Level() {
    }

    public Level(String name) {
        this.name = name;
    }

    public Level(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Level level)) return false;
        return id.equals(level.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



