package Arthub.repository;

import Arthub.entity.TagArt;

import java.util.List;


public interface TagArtRepository {
    void addTagArtUserIdAndTagId(List<TagArt> tagArtList,int artworkId);

    int getTagIdByArtId(int ArtworkId);

    void deleteTagArtByArtId(int artworkId);
}
