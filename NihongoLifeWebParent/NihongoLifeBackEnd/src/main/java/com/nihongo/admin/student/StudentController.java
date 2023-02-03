package com.nihongo.admin.student;

import com.nihongo.common.entity.Student;
import com.nihongo.common.entity.StudentDTO;
import com.nihongo.common.exception.ExistStudentException;
import com.nihongo.common.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/api/student/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO studentDTO) throws ExistStudentException {
        Student student=new Student();
        student.setName(studentDTO.getName());
        student.setPassword(studentDTO.getPassword());
        student.setEmail(studentDTO.getEmail());
        try {
            service.save(student);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }catch (ExistStudentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Integer id) throws StudentNotFoundException {
        try {
            Student student= service.get(id);
            return new ResponseEntity<>(student, HttpStatus.FOUND);
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/api/student/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Student student){
        try {
            service.update(id, student);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ExistStudentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/student")
    public ResponseEntity<List<Student>> studentList(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }



}
