package com.sivalabs.myservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.myservice.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
	@Query("select u from User u where u.email=?1 and u.password=?2")
	Optional<User> login(String email, String password);

    Optional<User> findByEmail(String email);
}
