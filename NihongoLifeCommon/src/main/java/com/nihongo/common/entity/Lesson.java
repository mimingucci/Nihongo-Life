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

    @Column(name = "likes")
    private Integer like;

    @Column(name = "dislikes")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTimeToRead() {
		return timeToRead;
	}

	public void setTimeToRead(Integer timeToRead) {
		this.timeToRead = timeToRead;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
    
}
