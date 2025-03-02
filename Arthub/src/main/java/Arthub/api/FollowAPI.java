package Arthub.api;

import Arthub.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Follow")
public class FollowAPI {
    @Autowired
    FollowRepository followRepository;

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
}
