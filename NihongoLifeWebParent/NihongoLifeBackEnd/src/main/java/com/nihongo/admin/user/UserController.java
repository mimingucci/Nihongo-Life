package com.nihongo.admin.user;

import com.nihongo.common.entity.Role;
import com.nihongo.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/api/user/created")
    public ResponseEntity<?> createUser(@RequestBody User user) throws UserNotFoundException{
        user.setEnabled(true);
        user.setRoles(Set.of(new Role(1)));
        user.setCreatedTime(new Date());
        try {
        	service.newUser(user);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
        
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
    
    
    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Integer id) {
    	try {
			User user=service.get(id);
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
    
    @DeleteMapping("/api/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Integer id) throws UserNotFoundException{ 
    	try {
    		service.deleteUser(id);
    		return new ResponseEntity<>("Done", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    	
    }
    
    @PutMapping("/api/user/update")
    public ResponseEntity<?> updateFullUser(@RequestBody User user, @RequestParam("image") MultipartFile multipartFile) throws IOException{
    	if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			User savedUser = service.updateAccount(user);
			
			String uploadDir = "user-photos/" + savedUser.getId();
		    com.nihongo.FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);			
		} else {
			if (user.getPhotos().isEmpty()) user.setPhotos(null);
			service.save(user);
		}
    	return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
    
    @GetMapping("/api/user")
    public ResponseEntity<List<User>> userList(){
    	List<User> users=service.listAll();
    	if(users.isEmpty()) {
    		return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @PatchMapping("/api/user/{id}/{enable}")
    public void updateEnable(@PathVariable(name = "id") Integer id,@PathVariable(name = "enable") Boolean enable) {
    	service.updateUserEnabledStatus(id, enable);
    }

}
