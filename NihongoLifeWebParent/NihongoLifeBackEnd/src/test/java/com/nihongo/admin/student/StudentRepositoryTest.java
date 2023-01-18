package com.nihongo.admin.student;

import com.nihongo.admin.message.MessageRepository;
import com.nihongo.common.entity.AuthenticationType;
import com.nihongo.common.entity.Message;
import com.nihongo.common.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Test
    public void updateTotalScore(){
        repo.updateTotalScore(100, 1);
        assert true;
    }

    @Test
    public void listByPage(){
        Pageable pageable= PageRequest.of(0, 10);
        Page<Student> students=repo.listByPage("Van A", pageable);
        System.out.println(students.getContent().get(0).toString());
        assert students.getTotalElements()>0;
    }
}
