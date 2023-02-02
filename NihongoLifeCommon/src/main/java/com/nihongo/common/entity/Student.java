package com.nihongo.common.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
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
        this.totalScore=0;
    }

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.totalScore=0;
    }

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public AuthenticationType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthenticationType authType) {
        this.authType = authType;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Set<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(Set<Quiz> quiz) {
        this.quiz = quiz;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Set<Message> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Set<Message> sendMessages) {
        this.sendMessages = sendMessages;
    }

    public Set<Message> getReceiveMessages() {
        return receiveMessages;
    }

    public void setReceiveMessages(Set<Message> receiveMessages) {
        this.receiveMessages = receiveMessages;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", totalScore=" + totalScore +
                ", authType=" + authType +
                ", enabled=" + enabled +
                '}';
    }
}
