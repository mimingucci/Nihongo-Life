package com.nihongo.admin.student;

import com.nihongo.common.entity.AuthenticationType;
import com.nihongo.common.entity.Student;
import com.nihongo.common.exception.ExistStudentException;
import com.nihongo.common.exception.StudentNotFoundException;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Student student) throws ExistStudentException {
        Student savedStudent=repo.findStudentByEmail(student.getEmail());
        if(savedStudent!=null){
            throw new ExistStudentException("There is an exist account with email "+student.getEmail());
        }else{
            UUID uuid=UUID.randomUUID();
            student.setEnabled(false);
            student.setCreatedTime(new Date());
            student.setTotalScore(0);
            student.setAuthType(AuthenticationType.DATABASE);
            student.setVerificationCode(uuid.toString().replace("-", ""));
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            repo.save(student);
        }
    }

    public void updateStudentEnableStatus(Integer id, Boolean enable){
        repo.updateEnabledStatus(id, enable);
    }

    public Student get(Integer id) throws StudentNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Could not find any students with ID " + id);
        }
    }

    public List<Student> findAll(){
        List<Student> studentList=new ArrayList<>();
        repo.findAll().forEach(studentList::add);
        return studentList;
    }

    public boolean isEmailEnique(Integer id, String email){
        Student existStudent=repo.findStudentByEmail(email);
        if(existStudent!=null && existStudent.getId()!=id){
            return false;
        }
        return true;
    }

    public void delete(Integer id) throws StudentNotFoundException {
        Long count=repo.countById(id);
        if(count==null || count==0){
            throw new StudentNotFoundException("Could not find any customers with ID " + id);
        }
        repo.deleteById(id);
    }

    public void update(Integer id, Student student) throws ExistStudentException, StudentNotFoundException {
        Student existStudent=repo.findById(id).get();
        if(existStudent == null){
            save(student);
        }
        if(existStudent.getId()!=id){
            throw new StudentNotFoundException("Cound not find any student with id: "+id);
        }

        existStudent.setName(student.getName());
        existStudent.setPassword(student.getPassword());
        existStudent.setEnabled(student.isEnabled());
        repo.save(existStudent);
    }

}
