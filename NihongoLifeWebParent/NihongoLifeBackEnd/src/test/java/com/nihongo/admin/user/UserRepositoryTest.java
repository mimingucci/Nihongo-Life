package com.nihongo.admin.user;

import com.nihongo.admin.role.RoleRepository;
import com.nihongo.common.entity.Role;
import com.nihongo.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void saveUser(){
        User user=new User();
        user.setEmail("gtvvunguye@gmail.com");
        user.setEnabled(true);
        user.setFirstName("Vu");
        user.setLastName("Nguyen Tien");
        user.setPassword("toivaban12345");
        User savedUser=repo.save(user);
        assert  savedUser.getId()>0;
    }

    @Test
    public void deleteUser(){
        repo.deleteById(1);
        assert true;
    }

    @Test
    public void saveUserWithRole(){
       User user=repo.findById(2).get();
        Set<Role> roles=new HashSet<>();
        Role role=roleRepo.findById(1).get();
        roles.add(role);
       user.setRoles(roles);
       User savedUser=repo.save(user);
       assert savedUser.getRoles().size()>0;
    }

    @Test
    public void getUser(){
        User user=repo.findById(2).get();
        System.out.println(user.getRoles().size());
        assert user.getFirstName().length()>0;
    }

    @Test
    public void getUserByEmail(){
        User user=repo.findUserByEmail("gtvvbahbj");
        System.out.println(user.getFirstName());
        assert user!=null;
    }

    @Test
    public void updateEnabledUser(){
        repo.updateEnable(2, false);
        assert true;
    }

    @Test
    public void pagingUser(){
        Pageable pageable= PageRequest.of(0, 5);
        Page<User> users=repo.listUsersByPage("Vu", pageable);
        assert users.getSize()>0;
    }

    @Test
    public void updateUserEnabled(){
        repo.updateEnabledStatus(2, true);
        assert true;
    }

    @Test
    public void findUserByEmail(){
         User user=repo.findByEmail("gtvvunguye@gmail.com");
         assert user.getId()>0;
    }

    @Test
    public void getAll(){
        List<User> users=repo.findAll(Sort.by("firstName").ascending());
        assert !users.isEmpty();
    }

}
