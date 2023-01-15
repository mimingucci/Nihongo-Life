package com.nihongo.admin.message;

import com.nihongo.common.entity.Message;
import com.nihongo.common.entity.Student;
import com.nihongo.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    public List<Message> findMessageBySenderAndRecipient(Student sender, Student recipient);
    public List<Message> findMessageBySender(Student sender);
    public List<Message> findMessageByRecipient(Student recipient);
}
