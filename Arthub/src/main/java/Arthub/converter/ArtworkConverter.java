package Arthub.converter;

import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.entity.TagArt;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ArtworkConverter {
    public Artwork convertArtworkDTOToArtworkEntity(ArtworkDTO artworkDTO) {
        Artwork artwork = new Artwork();
        artwork.setArtworkID(artworkDTO.getArtworkID());
        artwork.setArtworkName(artworkDTO.getArtworkName());
        artwork.setDescription(artworkDTO.getDescription());
        artwork.setPurchasable(artworkDTO.isPurchasable());
        artwork.setPrice(artworkDTO.getPrice());
        artwork.setImageFile(artworkDTO.getImageFile());
        artwork.setCreatorID(artworkDTO.getCreatorID());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);
        artwork.setDateCreated(formattedDate); // Giả sử DateCreated trong Artwork là String

        artwork.setStatus(1);
        artwork.setFavorites(artworkDTO.getFavorites());
        return artwork;
    }

    public ArtworkDTO convertArtworkEntityToArtworkDTO(Artwork artwork,List<TagArt> tagArtList) {
        ArtworkDTO artworkDTO = new ArtworkDTO();
        artworkDTO.setArtworkID(artwork.getArtworkID());
        artworkDTO.setArtworkName(artwork.getArtworkName());
        artworkDTO.setDescription(artwork.getDescription());
        artworkDTO.setPurchasable(artwork.isPurchasable());
        artworkDTO.setPrice(artwork.getPrice());
        artworkDTO.setImageFile(artwork.getImageFile());
        artworkDTO.setStatus(artwork.getStatus());

//        if (tagArtList != null) {
//            List<Integer> tagIDs = tagArtList.stream()
//                    .map(TagArt::getTagID)
//                    .collect(Collectors.toList());
//            artworkDTO.setTags(tagIDs); // Giả sử ArtworkDTO có `setTags(List<Integer>)`
//        }
        return artworkDTO;
    }
}