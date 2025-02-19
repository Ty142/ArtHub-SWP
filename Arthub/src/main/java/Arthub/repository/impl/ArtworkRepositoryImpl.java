package Arthub.repository.impl;

import Arthub.entity.Artwork;
import Arthub.entity.ArtworkTag;
import Arthub.entity.Tag;
import Arthub.repository.ArtworkRepository;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArtworkRepositoryImpl implements ArtworkRepository {

    @Override
    public List<Artwork> getTop10LikedArtworks() {
        String sql = "SELECT TOP 10 a.*, ta.TagArtID, ta.TagID " +
                "FROM Artworks a " +
                "LEFT JOIN TagArt ta ON a.ArtworkID = ta.ArtworkID " +
                "ORDER BY (COALESCE(a.Views, 0) * 0.5 + COALESCE(a.Likes, 0) * 1 " +
                "+ COALESCE(a.Comments, 0) * 1.25 + COALESCE(a.Favorites, 0) * 1.5) DESC";

        List<Artwork> artworks = new ArrayList<>();
        Map<Integer, Artwork> artworkMap = new HashMap<>();
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int artworkID = resultSet.getInt("ArtworkID");

                if (!artworkMap.containsKey(artworkID)) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(artworkID);
                    artwork.setCreatorID(resultSet.getInt("CreatorID"));
                    artwork.setArtworkName(resultSet.getString("ArtworkName"));
                    artwork.setDescription(resultSet.getString("Description"));
                    artwork.setDateCreated(resultSet.getTimestamp("DateCreated"));
                    artwork.setLikes(resultSet.getInt("Likes"));
                    artwork.setViews(resultSet.getInt("Views"));
                    artwork.setComments(resultSet.getInt("Comments"));
                    artwork.setFavorites(resultSet.getInt("Favorites"));
                    artwork.setPurchasable(resultSet.getBoolean("Purchasable"));
                    artwork.setPrice(resultSet.getDouble("Price"));
                    artwork.setImageFile(resultSet.getString("ImageFile"));
                    artwork.setArtworkTags(new ArrayList<>());
                    artworkMap.put(artworkID, artwork);
                }

                int tagId = resultSet.getInt("TagID");

                if (tagId > 0) {
                    ArtworkTag artworkTag = new ArtworkTag(resultSet.getInt("TagArtID"), artworkID, tagId);
                    artworkMap.get(artworkID).getArtworkTags().add(artworkTag);
                }




            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(artworkMap.values());
    }
}
