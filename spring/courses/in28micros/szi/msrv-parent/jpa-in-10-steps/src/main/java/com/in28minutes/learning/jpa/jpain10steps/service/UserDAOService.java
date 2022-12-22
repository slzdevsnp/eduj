package com.in28minutes.learning.jpa.jpain10steps.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

@Repository
@Transactional
public class UserDAOService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(User user){
        entityManager.persist(user);
        return user.getId();
    }
}

/*

    User jack = new User("Jack", "Admin");
    User  jill = new User("Jill", "Admin");
    entityManager.persist(jack);
    //persistence Context is started, jack object is started to be tracking
    jack.setRole("User");

 */
