package Arthub.dto;

public class FileUploadDTO {
    // Chuỗi Base64 của ảnh, có thể bao gồm phần header như "data:image/jpeg;base64,"
    private String imageFile;

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
