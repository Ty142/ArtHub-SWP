package Arthub.dto;

public class FileUploadDTO {
    // Chuỗi Base64 của ảnh, có thể bao gồm phần header như "data:image/jpeg;base64,"
    private String base64Data;
    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }
}
