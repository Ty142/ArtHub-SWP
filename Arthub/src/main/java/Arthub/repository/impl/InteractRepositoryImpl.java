package Arthub.repository.impl;

import Arthub.entity.Interact;
import Arthub.repository.InteractRepository;
import Arthub.entity.Artwork;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InteractRepositoryImpl implements InteractRepository {
    private static final int FAVOURITE_ACTIVITY_ID = 1;
    private static final int LIKE_ACTIVITY_ID = 2;

    //-------- Start Favourites ----------
    @Override
    public boolean toggleFavourite(int userID, int artworkID) {
        String deleteSQL = "DELETE FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 1";
        String insertSQL = "INSERT INTO Interact (UserID, ArtworkID, ActivityId, DateOfInteract) VALUES (?, ?, 1, GETDATE())";
        String updateFavouriteSQL = "UPDATE Artworks SET Favorites = Favorites + ? WHERE ArtworkID = ?";

        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection()) {
            if (isFavourite(userID, artworkID)) {
                // Nếu đã favourite -> Unfavourite
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
                     PreparedStatement updateFavouriteStatement = connection.prepareStatement(updateFavouriteSQL)) {
                    deleteStatement.setInt(1, userID);
                    deleteStatement.setInt(2, artworkID);
                    deleteStatement.executeUpdate();

                    updateFavouriteStatement.setInt(1, -1); // Giảm 1 Favourite
                    updateFavouriteStatement.setInt(2, artworkID);
                    updateFavouriteStatement.executeUpdate();
                }
                return false; // Đã Unfavourite
            } else {
                // Nếu chưa favourite -> Favourite
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
                     PreparedStatement updateFavouriteStatement = connection.prepareStatement(updateFavouriteSQL)) {
                    insertStatement.setInt(1, userID);
                    insertStatement.setInt(2, artworkID);
                    insertStatement.executeUpdate();

                    updateFavouriteStatement.setInt(1, 1); // Tăng 1 Favourite
                    updateFavouriteStatement.setInt(2, artworkID);
                    updateFavouriteStatement.executeUpdate();
                }
                return true; // Đã Favourite
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean isFavourite(int userID, int artworkID) {
        String checkExistSQL = "SELECT COUNT(*) FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 1";
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement checkStatement = connection.prepareStatement(checkExistSQL)) {

            checkStatement.setInt(1, userID);
            checkStatement.setInt(2, artworkID);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Trả về true nếu đã Favourite, false nếu chưa
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }


    @Override
    public List<Artwork> getFavouriteArtworks(int userID) {
        String sql = "SELECT a.* FROM Artworks a " +
                "JOIN Interact i ON a.ArtworkID = i.ArtworkID " +
                "WHERE i.UserID = ? AND i.ActivityId = 1"; // ActivityID = 1 là Favourite

        List<Artwork> favouriteArtworks = new ArrayList<>();
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Artwork artwork = new Artwork();
                artwork.setArtworkID(resultSet.getInt("ArtworkID"));
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
                favouriteArtworks.add(artwork);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return favouriteArtworks;
    }
    //-------- End Favourites ----------


    //-------- Start Like ----------
//
    @Override
    public boolean toggleLike(int userID, int artworkID) {
        String deleteSQL = "DELETE FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 2";
        String insertSQL = "INSERT INTO Interact (UserID, ArtworkID, ActivityId, DateOfInteract) VALUES (?, ?, 2, GETDATE())";
        String updateLikeSQL = "UPDATE Artworks SET Likes = COALESCE(Likes, 0) + ? WHERE ArtworkID = ?";
        String selectLikeSQL = "SELECT Likes FROM Artworks WHERE ArtworkID = ?";

        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection()) {
            if (isLike(userID, artworkID)) {
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
                     PreparedStatement updateLikeStatement = connection.prepareStatement(updateLikeSQL);
                     PreparedStatement selectStatement = connection.prepareStatement(selectLikeSQL)) {
                    deleteStatement.setInt(1, userID);
                    deleteStatement.setInt(2, artworkID);
                    deleteStatement.executeUpdate();

                    selectStatement.setInt(1, artworkID);
                    ResultSet rs = selectStatement.executeQuery();
                    int currentLikes = 0;
                    if (rs.next()) {
                        currentLikes = rs.getInt("Likes");
                        System.out.println("Likes before unlike: " + currentLikes);
                    }

                    // Chỉ giảm Likes nếu hiện tại > 0
                    if (currentLikes > 0) {
                        updateLikeStatement.setInt(1, -1);
                        updateLikeStatement.setInt(2, artworkID);
                        updateLikeStatement.executeUpdate();
                    }

                    rs = selectStatement.executeQuery();
                    if (rs.next()) {
                        System.out.println("Likes after unlike: " + rs.getInt("Likes"));
                    }
                }
                return false;
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
                     PreparedStatement updateLikeStatement = connection.prepareStatement(updateLikeSQL);
                     PreparedStatement selectStatement = connection.prepareStatement(selectLikeSQL)) {
                    insertStatement.setInt(1, userID);
                    insertStatement.setInt(2, artworkID);
                    insertStatement.executeUpdate();

                    selectStatement.setInt(1, artworkID);
                    ResultSet rs = selectStatement.executeQuery();
                    if (rs.next()) {
                        System.out.println("Likes before like: " + rs.getInt("Likes"));
                    }

                    updateLikeStatement.setInt(1, 1);
                    updateLikeStatement.setInt(2, artworkID);
                    updateLikeStatement.executeUpdate();

                    rs = selectStatement.executeQuery();
                    if (rs.next()) {
                        System.out.println("Likes after like: " + rs.getInt("Likes"));
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isLike(int userID, int artworkID) {
        String checkExistSQL = "SELECT COUNT(*) FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 2";
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement checkStatement = connection.prepareStatement(checkExistSQL)) {

            checkStatement.setInt(1, userID);
            checkStatement.setInt(2, artworkID);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Trả về true nếu đã Like, false nếu chưa
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }


    @Override
    public List<Artwork> getLikeArtworks(int userID) {
        String sql = "SELECT a.* FROM Artworks a " +
                "JOIN Interact i ON a.ArtworkID = i.ArtworkID " +
                "WHERE i.UserID = ? AND i.ActivityId = 2"; // ActivityID = 2 là Like

        List<Artwork> likeArtworks = new ArrayList<>();
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Artwork artwork = new Artwork();
                artwork.setArtworkID(resultSet.getInt("ArtworkID"));
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
                likeArtworks.add(artwork);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return likeArtworks;
    }

    @Override
    public int getLikeCount(int artworkID) {
        String sql = "SELECT Likes FROM Artworks WHERE ArtworkID = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artworkID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Likes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //-------- End Like ----------

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Interact interact) {
        String sql = "INSERT INTO Interact (ArtworkID, UserID, ActivityID, DateOfInteract) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, interact.getArtworkID(), interact.getUserID(), interact.getActivityID(), interact.getDateOfInteract());
    }

    public List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date) {
        String sql = "SELECT * FROM Interact WHERE ArtworkID = ? AND UserID = ? AND ActivityID = ? AND DateOfInteract = ?";
        return jdbcTemplate.query(sql, new Object[]{artworkID, userID, activityID, date}, new BeanPropertyRowMapper<>(Interact.class));
    }

    @Override
    public void deleteInteractByArtworkID(int artworkID) {
        String sql = "DELETE FROM Interact WHERE artworkID = ?";
            try  {
                ConnectUtils db = ConnectUtils.getInstance();
                Connection connection = db.openConection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, artworkID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }



