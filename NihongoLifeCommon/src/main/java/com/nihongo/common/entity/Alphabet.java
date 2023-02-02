package com.nihongo.common.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SortNatural;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
@Entity
@Table(name = "alphabet")
public class Alphabet extends Studying{
    @ManyToMany(mappedBy = "alphabets")
    @SortNatural
    private SortedSet<Letter> letters=new TreeSet<>();

}
