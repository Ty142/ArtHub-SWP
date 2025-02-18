package Arthub.api;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.dto.FileUploadDTO;
import Arthub.entity.Artwork;
import Arthub.repository.ArtworkRepository;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/Artworks")
public class ArtworkAPI {

    @Autowired
    UserService userService;
    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    ArtworkConverter artworkConverter;

    utils.ImageUtils imageUtils = new utils.ImageUtils();
    @PostMapping
    public ResponseEntity<String> createArtImg(@RequestBody ArtworkDTO artworkDTO ) {
            Artwork artwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO,artworkDTO.getTags());
            try {
                byte[] imgByte = imageUtils.decodeBase64(artwork.getImageFile());
                artwork.setImageFile( userService.uploadAvatar(imgByte, 3));
                artworkRepository.addArtwork(artwork);
                return ResponseEntity.ok("Upload Artwork Successfull: ");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh: " + e.getMessage());
            }
        }
    }


