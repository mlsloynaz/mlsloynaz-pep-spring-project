package com.example.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    Message save(Message message);
    void deleteById(Integer id);
    List<Message> findAll();
    Optional<Message> findById(Integer messageId);
    List<Message> findByPostedBy(Integer postedBy);
    
}
