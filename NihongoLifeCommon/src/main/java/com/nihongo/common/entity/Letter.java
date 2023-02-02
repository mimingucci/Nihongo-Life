package com.nihongo.common.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "letters")
public class Letter implements  Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "japanese_letter", nullable = false, unique = true)
    private String japaneseLetter;
    @Column(name = "latinh_letter", nullable = false)
    private String latinhLetter;
    private String pronunciation;
    @Column(name = "examples", nullable = true)
    private Set<String> examples;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "letter_alphabet",
            joinColumns = @JoinColumn(name = "letter_id"),
            inverseJoinColumns = @JoinColumn(name = "alphabet_id")
    )
    private Set<Alphabet> alphabets=new HashSet<>();

    public Letter(String japaneseLetter, String latinhLetter) {
        this.japaneseLetter = japaneseLetter;
        this.latinhLetter=latinhLetter;
    }



    @Override
    public int compareTo(Object o) {
        Letter convertLetter=(Letter) o;
        return this.japaneseLetter.compareTo(convertLetter.japaneseLetter);
    }
}
