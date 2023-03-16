package com.nihongo.admin.lesson;

import com.nihongo.common.entity.Lesson;
import com.nihongo.common.entity.Level;
import com.nihongo.common.exception.LessonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    public static final Logger LOGGER= LoggerFactory.getLogger(LessonController.class);

    private LessonService service;
    private LessonRepository repo;
    @GetMapping(path = "/{level}", produces = "application/json")
    public ResponseEntity<List<Lesson>> getAllLessonByLevel(@PathVariable(name = "level") Level level){
        List<Lesson> lessons=service.listLessonsByLevel(level);
        return ResponseEntity.ok(lessons);
    }
    @GetMapping(path = "/{level}/{id}")
    public ResponseEntity<?> getLesson(@PathVariable("level") Level levelId, @PathVariable("id") Integer lessonId){
          Level level=new Level(lessonId);
          Lesson lesson=null;
          try {
              lesson=service.findLessonByIdAndLevel(lessonId, level);
          } catch (LessonNotFoundException e) {
              LOGGER.info(e.getMessage());
              return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
          }
          return ResponseEntity.ok(lesson);
    }

}
