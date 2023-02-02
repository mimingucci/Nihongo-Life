package com.nihongo.admin.user;

import com.nihongo.common.entity.Role;
import com.nihongo.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserRepository repo;

    @PostMapping("/api/user/created")
    public ResponseEntity<?> createUser(User user){
        user.setEnabled(true);
        user.setRoles(Set.of(new Role(1)));
        user.setCreatedTime(new Date());
        repo.save(user);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

}
