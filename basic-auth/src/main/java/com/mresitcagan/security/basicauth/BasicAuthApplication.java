package com.mresitcagan.security.basicauth;

import com.mresitcagan.security.basicauth.dto.CreateUserRequest;
import com.mresitcagan.security.basicauth.model.Role;
import com.mresitcagan.security.basicauth.model.User;
import com.mresitcagan.security.basicauth.service.UserService;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class BasicAuthApplication implements CommandLineRunner {

	private final UserService userService;

    public BasicAuthApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
	}

	public void createDummyData(){
		CreateUserRequest request = CreateUserRequest
				.builder()
				.name("mrc")
				.username("user")
				.password("user")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.createUser(request);

		CreateUserRequest request2 = CreateUserRequest
				.builder()
				.name("mrc2")
				.username("admin")
				.password("admin")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.createUser(request2);

		CreateUserRequest request3 = CreateUserRequest
				.builder()
				.name("mrc3")
				.username("mod")
				.password("mod")
				.authorities(Set.of(Role.ROLE_MOD))
				.build();
		userService.createUser(request2);
	}

}
