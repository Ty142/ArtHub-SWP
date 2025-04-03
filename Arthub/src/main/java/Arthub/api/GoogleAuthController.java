package Arthub.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GoogleAuthController {

    private static final String GOOGLE_API_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    @PostMapping("/google-user")
    public ResponseEntity<?> getGoogleUser(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        if (token == null || token.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Token is required");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Gửi request GET đến API Google
            ResponseEntity<String> response = restTemplate.exchange(GOOGLE_API_URL, HttpMethod.GET, entity, String.class);

            // Nếu response thành công, trả về dữ liệu user
            return ResponseEntity.ok(response.getBody());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch user info from Google. Error: " + ex.getMessage());
        }
    }
}
