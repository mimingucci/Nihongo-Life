package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "video_lessons")
@Data
public class VideoLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "link", nullable = false, unique = true)
    private String link;
    private String title;
    private Integer like;
    private Integer dislike;
    @Column(name = "uploaded_time", nullable = false)
    private Date uploadedTime;
    private Integer runtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seminar_id")
    private Seminar seminar;

    public VideoLesson() {
    }

    public VideoLesson(String title ,String link, Integer runtime) {
        this.link = link;
        this.title = title;
        this.runtime = runtime;
        this.dislike=0;
        this.like=0;
        this.uploadedTime=new Date();
    }
}
