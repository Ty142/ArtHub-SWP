package Arthub.api;

import Arthub.service.InteractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interact")
public class InteractAPI {
    private final InteractService interactService;

    public InteractAPI(InteractService interactService) {
        this.interactService = interactService;
    }

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
    public ResponseEntity<List<Integer>> getFavouriteArtworks(@PathVariable int userID) {
        List<Integer> favourites = interactService.getFavouriteArtworks(userID);
        return ResponseEntity.ok(favourites);
    }

    @GetMapping("/favourite/status/{userID}/{artworkID}")
    public ResponseEntity<Boolean> isFavourite(@PathVariable int userID, @PathVariable int artworkID) {
        boolean isFavourite = interactService.isFavourite(userID, artworkID);
        return ResponseEntity.ok(isFavourite);
    }


}
