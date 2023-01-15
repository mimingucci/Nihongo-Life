package com.nihongo;

import com.nihongo.admin.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NihongoLifeBackEndApplicationTests {

	@Autowired
	private UserRepository repo;
	@Test
	void contextLoads() {
	}

}
