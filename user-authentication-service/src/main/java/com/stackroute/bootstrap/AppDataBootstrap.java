package com.stackroute.bootstrap;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppDataBootstrap implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public AppDataBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1=new User("john@abc.com","password1");
        User user2=new User("alice@xyz.com","password2");
        //userRepository.save(user1);
        //userRepository.save(user2);
    }
}
