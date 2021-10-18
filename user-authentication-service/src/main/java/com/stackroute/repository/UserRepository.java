package com.stackroute.repository;

import com.stackroute.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*This is the Repository class which extends JpaRepository*/
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /*This method will find the user by the id and password*/
    User findByIdAndPassword(String id, String password);

}
