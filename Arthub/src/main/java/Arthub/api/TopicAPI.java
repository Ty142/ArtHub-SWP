package Arthub.api;


import Arthub.dto.ThreadDTO;
import Arthub.dto.TopicDTO;
import Arthub.entity.Thread;
import Arthub.entity.Topic;
import Arthub.entity.TypeOfTopic;
import Arthub.repository.ThreadRepository;
import Arthub.repository.TopicRepository;
import Arthub.service.TopicService;
import Arthub.service.TypeOfTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Forum")
public class TopicAPI {

    @Autowired
    TypeOfTopicService typeOfTopicService;
    @Autowired
    TopicService topicService;
    @Autowired
    TopicRepository topicRepository;
    @GetMapping("/typeTopic")
    public List<TypeOfTopic> getTypeTopics() {
            return typeOfTopicService.GetAllTypeOfTopics();
    }

    @GetMapping("/topic/{TypeID}")
    public List<TopicDTO> getTopic(@PathVariable("TypeID") int typeID) {
            return topicService.getAllTopics(typeID);
    }

    @GetMapping("/{TopicID}/GetThread")
    public List<Thread> GetAllThread(@PathVariable("TopicID") int topicID) {
            return topicService.getAllThreads(topicID);
    }

    @PostMapping("/Thread")
    public ResponseEntity<String> InsertThread(@RequestBody ThreadDTO threadDTO){
        topicService.addTheThread(threadDTO);
        return ResponseEntity.ok("Thread created successfully");
    }

    @GetMapping("/Thread/{threadID}")
    public Thread GetThread(@PathVariable("threadID") int threadID) {
        return topicService.getThreadByThreadID(threadID);
    }


}
