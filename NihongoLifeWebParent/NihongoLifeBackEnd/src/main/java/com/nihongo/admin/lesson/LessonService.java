package com.nihongo.admin.lesson;

import com.nihongo.common.entity.Lesson;
import com.nihongo.common.entity.Level;
import com.nihongo.common.entity.Student;
import com.nihongo.common.exception.LessonNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LessonService {
    private LessonRepository repo;

    public Lesson getLessonById(Integer id) throws LessonNotFoundException {
        Lesson lesson=repo.getById(id);
        if(lesson==null){
            throw new LessonNotFoundException("Could not find lesson with id: "+id);
        }
        return lesson;
    }
    public List<Lesson> listLessonsByLevel(Level level){
        return repo.lessonsByLevel(level);
    }

    public Lesson findLessonByIdAndLevel(Integer id, Level level) throws LessonNotFoundException {
        Lesson existLesson=repo.findLessonByIdAndLevel(id, level);
        if(existLesson==null || existLesson.getId()!=id){
            throw new LessonNotFoundException("Cound not find lesson with id: "+id.toString());
        }
        return existLesson;
    }

    public void updateFullLesson(Integer id, Lesson lesson){
        Lesson existLesson=repo.getById(id);
        if(existLesson==null){
            repo.save(lesson);
        }else{
            existLesson.setContent(lesson.getContent());
//            existLesson.setDislike(lesson.getDislike());
            existLesson.setLogo(lesson.getLogo());
            existLesson.setTimeToRead(lesson.getTimeToRead());
//            existLesson.setLike(lesson.getLike());
            existLesson.setVideo(lesson.getVideo());
            existLesson.setMainImage(lesson.getMainImage());
            repo.save(existLesson);
        }
    }

    public void updateLikes(Integer lessonId, Integer studentId){
        Student student=new Student(studentId);
        Lesson lesson=repo.getById(lessonId);
        lesson.deleteStudentDislike(student);
        lesson.addStudentLike(student);
        repo.save(lesson);
    }

    public void updateDislikes(Integer lessonId, Integer studentId){
        Student student=new Student(studentId);
        Lesson lesson=repo.getById(lessonId);
        lesson.deleteStudentLike(student);
        lesson.addStudentDislike(student);
        repo.save(lesson);
    }

}
