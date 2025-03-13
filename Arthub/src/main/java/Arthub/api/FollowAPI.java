package Arthub.api;


import Arthub.entity.User;
import Arthub.repository.FollowRepository;
import Arthub.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Follow")
public class FollowAPI {
    @Autowired
    FollowRepository followRepository;

    @Autowired
    FollowService followService;

    @GetMapping("/checkFollow")
    public ResponseEntity<Map<String, Boolean>> checkFollowStatus(
            @RequestParam int followerID,
            @RequestParam int followingID
    ) {
        boolean isFollowing = followRepository.checkFollowExists(followerID, followingID);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isFollowing", isFollowing);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getFollowingUserFromFollowID(@PathVariable int id) throws SQLException {
        try {
        User follower = followService.getFollowingUserFromFollowID(id);
        return ResponseEntity.ok(follower);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
