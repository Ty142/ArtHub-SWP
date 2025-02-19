package Arthub.converter;

import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import org.springframework.stereotype.Component;

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
        artwork.setLibraryID(artworkDTO.getLibraryID());
        artwork.setStatus(1);
        return artwork;
    }

    public ArtworkDTO convertArtworkEntityToArtworkDTO(Artwork artwork) {
        ArtworkDTO artworkDTO = new ArtworkDTO();
        artworkDTO.setArtworkID(artwork.getArtworkID());
        artworkDTO.setArtworkName(artwork.getArtworkName());
        artworkDTO.setDescription(artwork.getDescription());
        artworkDTO.setPurchasable(artwork.isPurchasable());
        artworkDTO.setPrice(artwork.getPrice());
        artworkDTO.setImageFile(artwork.getImageFile());
        artworkDTO.setLibraryID(artwork.getLibraryID());
        artworkDTO.setStatus(artwork.getStatus());
        return artworkDTO;
    }
}
