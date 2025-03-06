package Arthub.api;


import Arthub.dto.ThreadDTO;
import Arthub.dto.TopicDTO;
import Arthub.entity.Comment;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/topic/title/{topicID}")
    public Topic GetTopic(@PathVariable("topicID") int topicID) {
        return topicRepository.getTopicByTopicID(topicID);
    }

    @GetMapping("/topic/comments/{ThreadID}")
    public List<Comment> GetThreadComments(@PathVariable("ThreadID") int threadID) {
        return topicService.getCommentByThreadID(threadID);
    }

    @PostMapping("/topic/comments/")
    public ResponseEntity<String> InsertComment(@RequestBody Comment comment){
        topicService.addCommentToThread(comment);
        return ResponseEntity.ok("Comment created successfully");
    }
    //Còn reply lấy trong replayCommentAPI nhé

    @PostMapping("topic/likes/toggel")
    public ResponseEntity<Map<String, Object>> toggleLike(@RequestBody Map<String, Integer> requestData) {
        int userID = requestData.get("userID");
        int ThreadID = requestData.get("ThreadID");

        boolean isLike = topicService.ToggleLikeThread(userID, ThreadID);
        int newLikeCount = topicService.getThreadLikeCount(ThreadID);

        Map<String, Object> response = new HashMap<>();
        response.put("isLike", isLike);
        response.put("likeCount", newLikeCount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("topic/likes/{userID}")
    public List<Thread> getLikedThreads(@PathVariable("userID") int userID) {
        return topicService.getLikedThreads(userID);
    }

    @GetMapping("topic/likes/{userID}/{threadID}")
    public boolean checkLike(@PathVariable("userID") int userID, @PathVariable("threadID") int threadID) {
        return topicService.isThreadLiked(userID, threadID);
    }

    @GetMapping("topic/likecount/{ThreadID}")
    public int getLikeCount(@PathVariable("ThreadID") int threadID) {
        return topicService.getThreadLikeCount(threadID);
    }
}
