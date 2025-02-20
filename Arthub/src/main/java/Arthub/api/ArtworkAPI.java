package Arthub.api;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.TagArtRepository;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Artworks")
public class ArtworkAPI {

    @Autowired
    UserService userService;
    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    ArtworkConverter artworkConverter;
    @Autowired
    TagArtRepository tagArtRepository;

    utils.ImageUtils imageUtils = new utils.ImageUtils();

    @PostMapping("/")
    public Artwork createArtImg(@RequestBody ArtworkDTO artworkDTO) {
        Artwork artwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO);
        try {
            byte[] imgByte = imageUtils.decodeBase64(artwork.getImageFile());
            artwork.setImageFile(userService.uploadAvatar(imgByte, 3,""));
            int id = artworkRepository.addArtwork(artwork);
            tagArtRepository.addTagArtUserIdAndTagId(artwork.getArtworkTags(), id);
            Optional<Artwork> artworkOpt = artworkRepository.getArtworkById(id);
            if (artworkOpt.isPresent()) {
                return artworkOpt.get();
            } else {
                throw new RuntimeException("Artwork không được tìm thấy sau khi tạo");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload ảnh: " + e.getMessage(), e);
        }
    }
}


