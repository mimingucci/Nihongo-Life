package com.nihongo.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "send_time", nullable = false)
    private Date sendTime;
    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student recipient;

    public Message(String content, Student sender, Student recipient) {
        this.content = content;
        this.sender=sender;
        this.recipient=recipient;
    }

    public Message() {
    }
}
