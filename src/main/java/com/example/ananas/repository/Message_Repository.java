package com.example.ananas.repository;

import com.example.ananas.entity.Messages;
import io.jsonwebtoken.security.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message_Repository extends JpaRepository<Messages, Integer> {
    List<Messages> findBySenderId(Integer senderId);
    List<Messages> findByReceiverId(Integer receiverId);
    List<Messages> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId);

    @Query(value = """
        SELECT * FROM (
                                   SELECT *, ROW_NUMBER() OVER (PARTITION BY sender_id ORDER BY created_at DESC) AS rn
                                   FROM ananas.messages
                               ) t
                               WHERE rn = 1
                               ORDER BY created_at ASC;
                               
    """, nativeQuery = true)
    List<Messages> findLatestMessagesBySender();

}
