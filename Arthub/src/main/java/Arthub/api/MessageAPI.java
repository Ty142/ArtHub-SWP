package Arthub.api;


import Arthub.entity.Message;
import Arthub.service.MessageService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/message")
public class MessageAPI {
    @Autowired
    private MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
