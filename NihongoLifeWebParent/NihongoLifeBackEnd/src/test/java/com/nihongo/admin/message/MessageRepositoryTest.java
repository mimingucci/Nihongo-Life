package com.nihongo.admin.message;

import com.nihongo.common.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class MessageRepositoryTest {
    @Autowired
    private MessageRepository repo;

    @Test
    public void findMessagesInPathTime(){
        Date dateA=new GregorianCalendar(2023, Calendar.JANUARY, 10).getTime();
        Date dateB=new Date();
        List<Message> messages=repo.findMessageFromDateAToDateB(dateA, dateB);
        System.out.println(messages.get(0));
        assert messages.size()>0;
    }
}
