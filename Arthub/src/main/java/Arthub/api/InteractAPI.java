package Arthub.api;

import Arthub.service.InteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InteractAPI {

    @Autowired
    private InteractService interactService;

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
