package com.nihongo.admin.question;

import com.nihongo.common.entity.Question;
import com.nihongo.common.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public Question findQuestionByContent(String content);

    @Query("SELECT q FROM Question q WHERE q.content LIKE %?1%")
    public Question findQuestionBySubContent(String content);

    public List<Question> findQuestionByQuiz(Quiz quiz);

    public Page<Question> findAll(Pageable pageable);

    @Query("SELECT q FROM Question q WHERE q.content LIKE %?1%")
    public Page<Question> findAll(String keyword, Pageable pageable);
}
