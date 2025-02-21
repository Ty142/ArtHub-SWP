package Arthub.repository.impl;

import Arthub.repository.InteractRepository;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InteractRepositoryImpl implements InteractRepository {
    private static final int FAVOURITE_ACTIVITY_ID = 1;

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
    public List<Integer> getFavouriteArtworks(int userID) {
        List<Integer> favourites = new ArrayList<>();
        String sql = "SELECT ArtworkID FROM Interact WHERE UserID = ? AND ActivityID = ?";
        try (Connection conn = ConnectUtils.getInstance().openConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, FAVOURITE_ACTIVITY_ID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                favourites.add(rs.getInt("ArtworkID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favourites;
    }
}
