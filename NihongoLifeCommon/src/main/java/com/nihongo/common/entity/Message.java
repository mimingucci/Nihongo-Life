package com.nihongo.common.entity;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "messages")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Student getSender() {
		return sender;
	}

	public void setSender(Student sender) {
		this.sender = sender;
	}

	public Student getRecipient() {
		return recipient;
	}

	public void setRecipient(Student recipient) {
		this.recipient = recipient;
	}
    
}
