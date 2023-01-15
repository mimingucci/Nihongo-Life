package com.nihongo.admin.question;

import com.nihongo.common.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public Question findQuestionByContent(String content);

    @Query("SELECT q FROM Question q WHERE q.content LIKE %?1%")
    public Question findQuestionBySubContent(String content);
}
