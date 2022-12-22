package com.in28minutes.learning.jpa.jpain10steps;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDAOService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner{

    private static final Logger log =
            LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);

    @Autowired
    private UserDAOService userDaoService;

    @Override
    public void run(String... arg0) throws Exception {
        User user = new User("Jack", "Admin");
        User user1 = new User("Marianne", "FullStackDev");
        User user2 = new User("Charlotte", "ProductManager");

        //New User is created : User [id=1, name=Jack, role=Admin]
        long insert = userDaoService.insert(user);
        long insert1 = userDaoService.insert(user1);
        long insert2 = userDaoService.insert(user2);
        log.info("New User is created : " + user);
        log.info("Also created user:" + user1);
        log.info("Also created user:" + user2);

    }
}