package com.nihongo.admin.role;

import com.nihongo.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository repo;

    @Test
    public void newRole(){
        Role role=new Role("ASSISTANT", "manage questions and reviews");
        Role savedRoles=repo.save(role);
        assert savedRoles.getId()>2;
    }



}
