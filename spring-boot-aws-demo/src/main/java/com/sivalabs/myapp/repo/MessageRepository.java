package com.sivalabs.myapp.repo;

import com.sivalabs.myapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
