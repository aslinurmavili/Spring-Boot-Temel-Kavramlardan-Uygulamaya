package com.asli.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asli.demo.entity.User;
import com.asli.demo.service.impl.UserServiceImpl;

@SpringBootApplication
public class SpringBootTemelKavramlardanUygulamayaApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringBootTemelKavramlardanUygulamayaApplication.class, args);

		UserServiceImpl userService = context.getBean(UserServiceImpl.class);


		User user = new User();
		user.setName("Main Test User");
		user.setEmail("main@example.com");
		userService.createUser(user);
		System.out.println("User created from main: " + user.getName());


		User rollbackUser = new User();
		rollbackUser.setName("Rollback Test User");
		rollbackUser.setEmail("rollback@example.com");
		try {
			userService.createUserWithRollback(rollbackUser, true); // true → exception fırlatır
		} catch (RuntimeException e) {
			System.out.println("Rollback test triggered: " + e.getMessage());
		}


		User isolationUser1 = new User();
		isolationUser1.setName("Isolation Test User 1");
		isolationUser1.setEmail("isolation1@example.com");
		userService.createUser(isolationUser1);

		User isolationUpdate = new User();
		isolationUpdate.setName("Updated Isolation User");
		isolationUpdate.setEmail("updated@example.com");


		userService.updateUserReadCommitted(isolationUser1.getId(), isolationUpdate);
		System.out.println("Isolation test READ_COMMITTED completed for user: " + isolationUser1.getId());


		userService.updateUserRepeatableRead(isolationUser1.getId(), isolationUpdate);
		System.out.println("Isolation test REPEATABLE_READ completed for user: " + isolationUser1.getId());
	}
}
