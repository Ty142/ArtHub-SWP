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
}
