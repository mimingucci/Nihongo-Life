package com.nihongo.common.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "logo", nullable = true)
    private String logo;
    @Column(nullable = false, length = 10000)
    private String content;

    private String video;

    private Integer like;

    private Integer dislike;
    @Column(name = "title")
    private String title;
    @Column(name = "time_to_read")
    private Integer timeToRead;
    @Column(name = "created_time", nullable = false)
    private Date createdOn;
    @Column(name = "main_image")
    private String mainImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    public Lesson(String name, String content, String title, Integer timeToRead) {
        this.name = name;
        this.content=content;
        this.title=title;
        this.timeToRead=timeToRead;
        this.like=0;
        this.dislike=0;
    }

    public Lesson() {
    }
}
