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
        String checkExistSQL = "SELECT COUNT(*) FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 1";
        String deleteSQL = "DELETE FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 1";
        String insertSQL = "INSERT INTO Interact (UserID, ArtworkID, ActivityId, DateOfInteract) VALUES (?, ?, 1, GETDATE())";
        String increaseFavouriteSQL = "UPDATE Artworks SET Favorites = Favorites + 1 WHERE ArtworkID = ?";
        String decreaseFavouriteSQL = "UPDATE Artworks SET Favorites = Favorites - 1 WHERE ArtworkID = ?";

        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement checkStatement = connection.prepareStatement(checkExistSQL)) {

            // Kiểm tra xem artwork đã được favourite chưa
            checkStatement.setInt(1, userID);
            checkStatement.setInt(2, artworkID);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Nếu đã favourite -> Unfavourite (xóa khỏi Interact + giảm Favourites)
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
                     PreparedStatement decreaseFavouriteStatement = connection.prepareStatement(decreaseFavouriteSQL)) {
                    deleteStatement.setInt(1, userID);
                    deleteStatement.setInt(2, artworkID);
                    deleteStatement.executeUpdate();

                    decreaseFavouriteStatement.setInt(1, artworkID);
                    decreaseFavouriteStatement.executeUpdate();
                }
                return false; // Đã xóa khỏi favourite (Unfavourite)
            } else {
                // Nếu chưa favourite -> Favourite (thêm vào Interact + tăng Favourites)
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
                     PreparedStatement increaseFavouriteStatement = connection.prepareStatement(increaseFavouriteSQL)) {
                    insertStatement.setInt(1, userID);
                    insertStatement.setInt(2, artworkID);
                    insertStatement.executeUpdate();

                    increaseFavouriteStatement.setInt(1, artworkID);
                    increaseFavouriteStatement.executeUpdate();
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
