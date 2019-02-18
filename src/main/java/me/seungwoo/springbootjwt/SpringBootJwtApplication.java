package me.seungwoo.springbootjwt;

import me.seungwoo.springbootjwt.domain.User;
import me.seungwoo.springbootjwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("seungwoo", "0000"));
            List<User> users = userRepository.findAll();
            System.out.println(users);
        };
    }
}
