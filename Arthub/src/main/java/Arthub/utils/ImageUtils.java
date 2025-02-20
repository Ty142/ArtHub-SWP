package utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtils {
    public byte[] decodeBase64(String base64){
        if(base64 == null || base64.isEmpty()) {
            throw new IllegalArgumentException("Base64 string cannot be null or empty");
        }
        if(base64.contains(",")) {
            base64 = base64.split(",")[1];
        }
        return Base64.getDecoder().decode(base64);
    }

    public String extractPublicId(String secureUrl) {
        // secureUrl mẫu:
        // "https://res.cloudinary.com/djprssm3o/image/upload/v1740039080/test/5ee8a5cc-a2d5-4027-956d-c31d71f257b4.jpg"
        if (secureUrl == null || secureUrl.isEmpty()) {
            return null;
        }
        String[] parts = secureUrl.split("/");
        if (parts.length >= 2) {
            String folder = parts[parts.length - 2];
            String fileNameWithExt = parts[parts.length - 1];
            int dotIndex = fileNameWithExt.lastIndexOf('.');
            String fileName = (dotIndex > 0) ? fileNameWithExt.substring(0, dotIndex) : fileNameWithExt;
            return folder + "/" + fileName;
        }
        return null;
    }
}
