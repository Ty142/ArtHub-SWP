package Arthub.repository;

import Arthub.entity.Artwork;
import Arthub.entity.Tag;
import Arthub.entity.TagArt;

import java.util.ArrayList;
import java.util.List;


public interface TagArtRepository {
    void addTagArtUserIdAndTagId(List<TagArt> tagArtList,int artworkId);

    int getTagIdByArtId(int ArtworkId);

    ArrayList<Tag> getAllTagArtByArtworkId(int artworkId);

    void deleteTagArtByArtId(int artworkId);


}
