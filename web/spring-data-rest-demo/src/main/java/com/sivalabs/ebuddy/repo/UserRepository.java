package com.sivalabs.ebuddy.repo;

import com.sivalabs.ebuddy.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String username);
}
