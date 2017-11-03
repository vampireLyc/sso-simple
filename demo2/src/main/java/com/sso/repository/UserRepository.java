package com.sso.repository;

import com.sso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Lyc on 2017/11/3.
 */
public interface UserRepository extends JpaRepository<User,Long>{

    @Query(value = "select * from user u where u.name = ?1 and u.password = ?2",nativeQuery = true)
    User findUserByNameAndPassword(String name, String password);
}
