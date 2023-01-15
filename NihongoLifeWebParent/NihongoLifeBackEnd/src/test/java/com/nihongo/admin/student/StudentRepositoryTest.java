package com.nihongo.admin.student;

import com.nihongo.admin.message.MessageRepository;
import com.nihongo.common.entity.AuthenticationType;
import com.nihongo.common.entity.Message;
import com.nihongo.common.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository repo;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void persistNewStudent(){
       // Student studentA=new Student("Nguyen Van A", "muvodoi@gmail.com", "toivaban12345", AuthenticationType.DATABASE,true, new Date());
        //Student studentB=new Student("Nguyen Trong B", "barcavodich@gmail.com", "Andrew1122", AuthenticationType.DATABASE,true, new Date());
        Student studentA=repo.findById(1).get();
        Student studentB=repo.findById(2).get();
        Message message=new Message("toi xem da bong nha", studentA, studentB);
        message.setSendTime(new Date());
       // Iterable<Student> students= repo.saveAll(Arrays.asList(studentA, studentB));

        Message savedMessage= messageRepository.save(message);
        //students.forEach(student -> System.out.println(student.getSendMessages()+" and "+student.getReceiveMessages()));
        assert savedMessage.getId()>0;
    }

    @Test
    public void getMessageFromStudent(){
        Student studentA=repo.findById(1).get();
        List<Message> messages=messageRepository.findMessageBySender(new Student(1));
        messages.forEach(message -> System.out.println(message.getContent()));
        assert true;
    }
}
