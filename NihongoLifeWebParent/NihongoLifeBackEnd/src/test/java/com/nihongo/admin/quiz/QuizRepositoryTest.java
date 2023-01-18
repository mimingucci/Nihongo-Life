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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        List<String> solutions=new ArrayList<>();
        solutions.add("a");
        solutions.add("b");
        solutions.add("c");
        solutions.add("d");
        Question question=new Question("さんぽします", solutions, "a");
        Quiz quiz=repo.findById(5).get();
        question.setQuiz(quiz);
        questions.add(question);
        Question savedQuestion=questionRepository.save(question);
        assert savedQuestion.getQuiz()!=null;
//        Quiz quiz=new Quiz("N5 Test", 100);
//        Quiz savedQuiz=repo.save(quiz);
//        assert savedQuiz.getId()>0 ;

    }

    @Test
    public void mergeQuestionToQuiz(){
        Question question=questionRepository.findById(1).get();
        Quiz quiz=repo.findById(5).get();
        Set<Question> questions=new HashSet<>();
        questions.add(question);
        quiz.setQuestions(questions);
        repo.save(quiz);
        assert true;
    }

    @Test
    public void mergeQuestionsToDetailsQuiz(){
        Quiz quiz=repo.findById(1).get();
        Question question=questionRepository.findById(1).get();
        Set<Question> questions=new HashSet<>();
        questions.add(question);
        quiz.setQuestions(questions);
        Quiz savedQuiz=repo.save(quiz);
        assert savedQuiz.getQuestions().size()>0;
    }


    @Test
    public void getInfoQuiz(){
        Quiz quiz=repo.findById(5).get();
       // System.out.println("question: ");
        //quiz.getQuestions().forEach(question -> System.out.println(question.getContent()));
        System.out.println(quiz.getQuestions().size());
        assert quiz.getQuestions().size()>0;
    }
}
