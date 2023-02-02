package com.nihongo;

import com.nihongo.admin.student.StudentRepository;
import com.nihongo.common.entity.AuthenticationType;
import com.nihongo.common.entity.Student;
import com.nihongo.common.entity.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MainController {
	@Autowired
	StudentRepository repo;

	@GetMapping("/hello")
	public String test() {
		return "index";
	}

	@PostMapping("/createNew")
	public ResponseEntity<StudentDTO> createNew(@RequestBody StudentDTO dto){
		Student student=new Student();
		student.setPassword(dto.getPassword());
		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setEnabled(true);
		student.setCreatedTime(new Date());
		student.setAuthType(AuthenticationType.DATABASE);
		Student reponseStd=repo.save(student);

		return ResponseEntity.ok(StudentDTO.convertStudentToDTO(reponseStd));
	}

	@GetMapping("/test")
	public void testRest(){
		System.out.println("Ok");
	}
}
