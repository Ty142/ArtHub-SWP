package Arthub.api;

import Arthub.entity.Artwork;
import Arthub.service.InteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
