package Arthub.api;

import Arthub.entity.Artwork;
import Arthub.service.InteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interact")
public class InteractAPI {
    @Autowired
    private InteractService interactService;


    public InteractAPI(InteractService interactService) {
        this.interactService = interactService;
    }

    // ------- Start Favourites API --------
    @PostMapping("/favourite/toggle")
    public ResponseEntity<String> toggleFavourite(@RequestBody Map<String, Integer> requestData) {
        int userID = requestData.get("userID");
        int artworkID = requestData.get("artworkID");

        boolean isFavourite = interactService.toggleFavourite(userID, artworkID);
        if (isFavourite) {
            return ResponseEntity.ok("Added to favourites successfully!");
        } else {
            return ResponseEntity.ok("Removed from favourites!");
        }
    }

    @GetMapping("/favourite/{userID}")
    public ResponseEntity<List<Artwork>> getFavouriteArtworks(@PathVariable int userID) {
        List<Artwork> favourites = interactService.getFavouriteArtworks(userID);
        return ResponseEntity.ok(favourites);
    }

    @GetMapping("/favourite/status/{userID}/{artworkID}")
    public ResponseEntity<Boolean> isFavourite(@PathVariable int userID, @PathVariable int artworkID) {
        boolean isFavourite = interactService.isFavourite(userID, artworkID);
        return ResponseEntity.ok(isFavourite);
    }
    // ------- End Favourites API --------
    // ------- Start Like API --------
    @PostMapping("/like/toggle")
    public ResponseEntity<Map<String, Object>> toggleLike(@RequestBody Map<String, Integer> requestData) {
        int userID = requestData.get("userID");
        int artworkID = requestData.get("artworkID");

        boolean isLike = interactService.toggleLike(userID, artworkID);
        int newLikeCount = interactService.getLikeCount(artworkID);

        Map<String, Object> response = new HashMap<>();
        response.put("isLike", isLike);
        response.put("likeCount", newLikeCount);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/like/{userID}")
    public ResponseEntity<List<Artwork>> getLikeArtworks(@PathVariable int userID) {
        List<Artwork> like = interactService.getLikeArtworks(userID);
        return ResponseEntity.ok(like);
    }

    @GetMapping("/like/status/{userID}/{artworkID}")
    public ResponseEntity<Boolean> isLike(@PathVariable int userID, @PathVariable int artworkID) {
        boolean isLike = interactService.isLike(userID, artworkID);
        return ResponseEntity.ok(isLike);
    }
    @GetMapping("/like/count/{artworkID}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable int artworkID) {
        int likeCount = interactService.getLikeCount(artworkID);
        return ResponseEntity.ok(likeCount);
    }
    // ------- End Like API --------

    @PutMapping("/api/interact/update")
    public String updateInteractData(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        if (message == null) {
            return "Error: Missing message parameter.";
        }
        try {
            interactService.saveInteractions();
            return "Interact data updated successfully!";
        } catch (Exception e) {
            return "Error updating interact data: " + e.getMessage();
        }
    }
}
