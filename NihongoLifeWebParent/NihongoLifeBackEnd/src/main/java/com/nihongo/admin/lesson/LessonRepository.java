package com.nihongo.admin.lesson;

import com.nihongo.common.entity.Lesson;
import com.nihongo.common.entity.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("SELECT ls FROM Lesson ls WHERE ls.level = :level")
    public List<Lesson> lessonsByLevel(Level level);

    public Lesson findLessonByIdAndLevel(Integer id, Level level);

    public Page<Lesson> findAll(Pageable pageable);

    @Query("SELECT ls FROM Lesson ls WHERE CONCAT(ls.name+' '+ls.content+' '+ls.title) LIKE %?1%")
    public Page<Lesson> findAllWithKeyword(String keyword, Pageable pageable);

    @Query("UPDATE Lesson ls SET ls.level = :level WHERE ls.id = :id")
    @Modifying
    public void updateLessonByLevel(Level level, Integer id);

    @Query("UPDATE Lesson ls SET ls.mainImage = :image WHERE ls.id = :id")
    @Modifying
    public void updateMainImage(String image, Integer id);

    @Query("UPDATE Lesson ls SET ls.content = :content WHERE ls.id = :id")
    @Modifying
    public void updateContent(String content, Integer id);
}
