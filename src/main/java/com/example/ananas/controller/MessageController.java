package com.example.ananas.controller;

import com.example.ananas.dto.MessageDTO;
import com.example.ananas.entity.Messages;
import com.example.ananas.service.Service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/message")
public class MessageController {
    MessageService messageService;

    @GetMapping("/send/{senderId}")
    public List<MessageDTO> getMessBySender(@PathVariable Integer senderId) {
        return messageService.getMessBySender(senderId);
    }

    @GetMapping("/receive/{receiverId}")
    public List<MessageDTO> getMessByReceiver(@PathVariable Integer receiverId) {
        return messageService.getMessByReceiver(receiverId);
    }

    @PostMapping
    public MessageDTO createMessage(@RequestBody MessageDTO message) {
        return messageService.sendMessage(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> updateMessage(@PathVariable Integer id, @RequestBody MessageDTO newMessage) {
        return ResponseEntity.ok(messageService.updateMessage(id, newMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest")
    public ResponseEntity<List<MessageDTO>> getLatestMessages() {
        return ResponseEntity.ok(messageService.getMessList());
    }
}