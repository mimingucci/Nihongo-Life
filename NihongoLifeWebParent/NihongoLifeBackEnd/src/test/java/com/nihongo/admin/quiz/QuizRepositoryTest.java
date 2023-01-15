package com.nihongo.admin.quiz;

import com.nihongo.admin.question.QuestionRepository;
import com.nihongo.admin.user.UserRepository;
import com.nihongo.common.entity.Question;
import com.nihongo.common.entity.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class QuizRepositoryTest {
    @Autowired
    private QuizRepository repo;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepo;
    @Test
    public void persistQuiz(){
        Set<Question> questions=new HashSet<>();
        questions.add(questionRepository.findById(1).get());
        Quiz quiz=new Quiz("Master N5 kanji", questions, 100);
        Quiz savedQuiz=repo.save(quiz);
        assert savedQuiz.getId()>0 ;
    }
}
