package com.nihongo.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

//    @Column(name = "likes")
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "lesson_like",
			joinColumns = @JoinColumn(name = "lesson_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
    private Set<Student> likes=new HashSet<>();

//    @Column(name = "dislikes")
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "lesson_dislike",
			joinColumns = @JoinColumn(name = "lesson_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
    private Set<Student> dislikes=new HashSet<>();
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
    @ManyToOne(fetch = FetchType.LAZY)
	private User author;

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Lesson(String name, String content, String title, Integer timeToRead) {
        this.name = name;
        this.content=content;
        this.title=title;
        this.timeToRead=timeToRead;
        this.likes=new HashSet<>();
        this.dislikes=new HashSet<>();
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

	public Set<Student> getLikes() {
		return likes;
	}

	public void setLikes(Set<Student> likes) {
		this.likes = likes;
	}

	public Set<Student> getDislikes() {
		return dislikes;
	}

	public void setDislikes(Set<Student> dislikes) {
		this.dislikes = dislikes;
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

	public void addStudentLike(Student student){
		this.likes.add(student);
	}

	public void addStudentDislike(Student student){
		this.dislikes.add(student);
	}

	public void deleteStudentLike(Student student){
		this.likes.remove(student);
	}

	public void deleteStudentDislike(Student student){
		this.dislikes.remove(student);
	}
}
