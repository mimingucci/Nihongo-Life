package com.nihongo.admin.question;

import com.nihongo.common.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository repo;

    @Test
    public void persistQuestion(){
        List<String> solutions=new ArrayList<>();
        solutions.add("a");
        solutions.add("b");
        solutions.add("c");
        solutions.add("d");
        Question question=new Question("さんぽします", solutions, "a");
        Question savedQuestion=repo.save(question);
        System.out.println(savedQuestion.getContent());
        assert savedQuestion.getId()>0;
    }

    @Test
    public void getQuestion(){
        Question question=repo.findById(1).get();
        //question.getSolutions().forEach((solution)-> System.out.println(solution));
        System.out.println(question.getContent());
        assert question.getContent().length()>0;
    }

    @Test
    public void findQuestionByContent(){
//        Question question=repo.findQuestionByContent("さんぽします");
//        assert question!=null;
        Question question=repo.findQuestionBySubContent("さんぽし");
        assert question!=null;
    }
}
