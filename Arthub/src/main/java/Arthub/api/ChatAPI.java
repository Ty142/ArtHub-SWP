package Arthub.api;


import Arthub.entity.Chat;
import Arthub.entity.Message;
import Arthub.entity.User;
import Arthub.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatAPI {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getChatProfileByUserId(@PathVariable int id) {
        List<User> users = chatService.getChatProfileByUserId(id);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @GetMapping("/ch/{id}")
    public ResponseEntity<List<Chat>> getChatByUserId(@PathVariable int id) {
        List<Chat> chats = chatService.getChatByUserId(id);
        if (chats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(chats, HttpStatus.OK);

        }
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<Message>> getMessageBySenderId(@PathVariable int id) {
        List<Message> messages = chatService.getMessageBySenderId(id);
        if (messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> createChat(@RequestBody Chat chat) {
        Boolean status = chatService.createChat(chat);
        if (status) {
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping("/")
    public ResponseEntity<String>  updateStatusChatByUserId(@RequestBody Chat chat) {
        chatService.updateStatusChatByUserId(chat.getUser1Id());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
