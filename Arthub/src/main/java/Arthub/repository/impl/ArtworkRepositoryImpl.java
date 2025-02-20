package Arthub.repository.impl;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.entity.ArtworkTag;
import Arthub.entity.Tag;
import Arthub.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.*;
import java.util.*;

@Repository
public class ArtworkRepositoryImpl implements ArtworkRepository {
    @Autowired
    private ArtworkConverter artworkConverter;

    @Override
    public void saveArtPicture(int id, String Artwork) {
        String sql = "UPDATE Artworks SET ImageFile = ? where ArtworkID = ?";
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Artwork);
            statement.setInt(2, id);
            statement.executeUpdate();
            connection.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addArtwork(ArtworkDTO artworkDTO) {
        String sql = "Insert into Artworks values ([ArtworkID],[ArtworkName],[Description],[Purchasable],[Price],[ImageFile],[UserID],[Status])";
        Artwork artwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO);
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, artwork.getArtworkID());
            statement.setString(2, artwork.getArtworkName());
            statement.setString(3, artwork.getDescription());
            statement.setBoolean(4, artwork.isPurchasable());
            statement.setDouble(5, artwork.getPrice());
            statement.setInt(6, artwork.getCreatorID());
            statement.setInt(7, artwork.getStatus());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Artwork> getArtworks() {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM Artworks";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Artwork artwork = new Artwork();
                artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                artwork.setArtworkName(resultSet.getString("ArtworkName"));
                artwork.setDescription(resultSet.getString("Description"));
                artwork.setPurchasable(resultSet.getBoolean("Purchasable"));
                artwork.setPrice(resultSet.getDouble("Price"));
                artwork.setCreatorID(resultSet.getInt("UserID"));
                artwork.setImageFile(resultSet.getString("ImageFile"));
                artwork.setLikes(resultSet.getInt("Likes"));
                artwork.setDateCreated(resultSet.getString("DateCreated"));
                artworks.add(artwork);
            }

            connection.close();
            statement.close();
            System.out.println("✅ Lấy thành công " + artworks.size() + " artworks từ database.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi SQL khi lấy artworks: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
        }
        return artworks;
    }

    @Override
    public Optional<Artwork> getArtworkById(int id) {
        String sql = "SELECT * FROM Artworks WHERE ArtworkID = ?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapResultSetToArtwork(resultSet));
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private Artwork mapResultSetToArtwork(ResultSet resultSet) throws SQLException {
        Artwork artwork = new Artwork();
        artwork.setArtworkID(resultSet.getInt("ArtworkID"));
        artwork.setArtworkName(resultSet.getString("ArtworkName"));
        artwork.setDescription(resultSet.getString("Description"));
        artwork.setPurchasable(resultSet.getBoolean("Purchasable"));
        artwork.setPrice(resultSet.getDouble("Price"));
        artwork.setCreatorID(resultSet.getInt("UserID"));
        artwork.setImageFile(resultSet.getString("ImageFile"));
        artwork.setLikes(resultSet.getInt("Likes"));
        artwork.setStatus(resultSet.getInt("Status"));
        artwork.setDateCreated(resultSet.getString("DateCreated"));
        return artwork;
    }

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
                    artwork.setDateCreated(resultSet.getString("DateCreated"));
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
