package com.nihongo.common.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "video_lesson")
public class VideoLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "link", nullable = false, unique = true)
    private String link;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "likes")
    private Integer like;
    @Column(name = "dislikes")
    private Integer dislike;
    @Column(name = "uploaded_time", nullable = false)
    private Date uploadedTime;
    @Column(name = "run_time")
    private Integer runtime;

    @ManyToOne
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Date getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(Date uploadedTime) {
        this.uploadedTime = uploadedTime;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

//    public Seminar getSeminar() {
//        return seminar;
//    }
//
//    public void setSeminar(Seminar seminar) {
//        this.seminar = seminar;
//    }
}
