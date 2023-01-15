package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 55)
    private String name;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "total_score")
    private Integer totalScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", length = 10)
    private AuthenticationType authType;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;

    @Column(name = "reset_password_token", length = 30)
    private String resetPasswordToken;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_quiz", joinColumns = @JoinColumn(name="student_id"), inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Set<Quiz> quiz=new HashSet<>();

    @Column(name = "created_time")
    private Date createdTime;

    @OneToMany(mappedBy = "sender", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Message> sendMessages;

    @OneToMany(mappedBy = "recipient", orphanRemoval = false, cascade = CascadeType.ALL)
    private Set<Message> receiveMessages=new HashSet<>();

    public Student(String name, String email, String password, AuthenticationType authType, boolean enabled, Date createdTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authType = authType;
        this.enabled = enabled;
        this.createdTime = createdTime;
    }

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }
}
