package Arthub.repository.impl;

import Arthub.dto.ActivityDTO;
import Arthub.entity.Interact;
import Arthub.entity.Thread;
import Arthub.repository.ActivityRepository;
import Arthub.repository.InteractRepository;
import Arthub.entity.Artwork;
import Arthub.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InteractRepositoryImpl implements InteractRepository {
    private static final int FAVOURITE_ACTIVITY_ID = 1;
    private static final int LIKE_ACTIVITY_ID = 2;

    @Autowired
    ActivityRepository repository;

    @Autowired
    UserRepository userRepository;
    //-------- Start Favourites ----------
    @Override
    public boolean toggleFavourite(int userID, int artworkID) {
        String sql = "{CALL ToggleFavouriteArtwork(?, ?, ?)}";
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            // Thiết lập tham số đầu vào
            callableStatement.setInt(1, userID);
            callableStatement.setInt(2, artworkID);
            callableStatement.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));

            // Thực thi stored procedure và lấy kết quả
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {
                boolean favouriteStatus = rs.getBoolean("FavouriteStatus");
                System.out.println("Favourite status: " + (favouriteStatus ? "Favourited" : "Unfavourited"));
                return favouriteStatus;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }


    @Override
    public boolean isFavourite(int userID, int artworkID) {
        String checkExistSQL = "SELECT COUNT(*) FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 1 AND Status = 1";
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
                artwork.setCreatorID(resultSet.getInt("UserID"));
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
        String sql = "{CALL ToggleLikeArtwork(?, ?, ?)}";
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, userID);
            callableStatement.setInt(2, artworkID);
            callableStatement.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));

            // Thực thi stored procedure và lấy kết quả
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {
                boolean likeStatus = rs.getBoolean("LikeStatus"); // BIT được ánh xạ thành boolean
                System.out.println("Like status: " + (likeStatus ? "Liked" : "Unliked"));
                return likeStatus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false; // Trả về false nếu có lỗi
    }

    @Override
    public boolean isLike(int userID, int artworkID) {
        String checkExistSQL = "SELECT COUNT(*) FROM Interact WHERE UserID = ? AND ArtworkID = ? AND ActivityId = 2 AND Status = 1";
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
                artwork.setCreatorID(resultSet.getInt("UserID"));
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

    public boolean ToggleLikeThread(int userID, int ThreadID) {
        String sql = "{CALL ToggleLikeForum(?, ?, ?)}";
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            // Thiết lập tham số đầu vào
            callableStatement.setInt(1, userID);
            callableStatement.setInt(2, ThreadID);
            callableStatement.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));

            // Thực thi stored procedure và lấy kết quả
            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {
                boolean likeStatus = rs.getBoolean("LikeStatus"); // BIT được ánh xạ thành boolean
                System.out.println("Like status: " + (likeStatus ? "Liked" : "Unliked"));
                return likeStatus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false; // Trả về false nếu có lỗi
    }
    public boolean isThreadLiked(int userID, int threadID) {
        String sql = "SELECT Status FROM Interact WHERE UserID = ? AND ThreadID = ? AND ActivityId = 2";
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setInt(2, threadID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("Status");
            }
            return false;
        } catch (Exception e) {
            System.err.println("SQL Error in isThreadLiked: " + e.getMessage());
            return false;
        }
    }

    public List<Thread> getLikedThreads(int userID) {
        String sql = "SELECT t.* FROM Thread t " +
                "JOIN Interact i ON t.ThreadID = i.ThreadID " +
                "WHERE i.UserID = ? AND i.ActivityId = 2 AND i.Status = 1";
        List<Thread> threads = new ArrayList<>();
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Thread thread = new Thread();
                thread.setThreadID(rs.getInt("ThreadID"));
                thread.setTitleThread(rs.getString("TitleThread"));
                thread.setThreadDescription(rs.getString("ThreadDescription"));
                thread.setLikes(rs.getInt("Likes"));
                thread.setComments(rs.getInt("Comments"));
                Timestamp sqlTimestamp = rs.getTimestamp("DateCreated");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    thread.setDateCreated(localDateTime);
                }
                thread.setTopicID(rs.getInt("TopicID"));
                thread.setUserID(rs.getInt("UserID"));
                threads.add(thread);
            }
        } catch (Exception e) {
            System.err.println("SQL Error in getLikedThreads: " + e.getMessage());
        }
        return threads;
    }

    public int getThreadLikeCount(int threadID) {
        String sql = "SELECT Likes FROM Thread WHERE ThreadID = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, threadID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("Likes");
            }
            return 0;
        } catch (Exception e) {
            System.err.println("SQL Error in getThreadLikeCount: " + e.getMessage());
            return 0;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Interact interact) {
        String sql = "INSERT INTO Interact (ArtworkID, UserID, ActivityID, DateOfInteract) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, interact.getArtworkID(), interact.getUserID(), interact.getActivityID(), interact.getDateOfInteract());
    }

    @Override
    public void saveInteractCommentOfForum(Interact interact) {
        String sql = "INSERT INTO interact(UserID, ActivityID, DateOfInteract, ThreadID) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, interact.getUserID(), interact.getActivityID(), interact.getDateOfInteract(), interact.getThreadID());
    }

    public List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date) {
        String sql = "SELECT * FROM Interact WHERE ArtworkID = ? AND UserID = ? AND ActivityID = ? AND DateOfInteract = ?";
        return jdbcTemplate.query(sql, new Object[]{artworkID, userID, activityID, date}, new BeanPropertyRowMapper<>(Interact.class));
    }

    @Override
    public List<Interact> findByThreadIDAndUserIDAndActivityID(int ThreadID, int userID, int activityID, String s) {
        String sql = "SELECT * FROM Interact WHERE ThreadID = ? AND UserID = ? AND ActivityID = ?";
        return jdbcTemplate.query(sql, new Object[]{ThreadID, userID, activityID}, new BeanPropertyRowMapper<>(Interact.class));
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

    @Override
    public List<ActivityDTO> getActivityList() {
        String sql = "Select * from [Interact]";
        List<ActivityDTO> activityList = new ArrayList<>();
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ActivityDTO activity = new ActivityDTO();
                int ActivityID = (resultSet.getInt("ActivityId"));
                activity.setActivityName(repository.getActivityNameByActivityID(ActivityID));
                Timestamp sqlTimestamp = resultSet.getTimestamp("DateOfInteract");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    activity.setActivityDate(localDateTime);
                }
                String owner = userRepository.getUserNameByArtworkID(resultSet.getInt("ArtworkID"),resultSet.getInt("ThreadID"));
                String UserInteraction = userRepository.getUserNameByUserID(resultSet.getInt("UserID"));
                activity.setOwnerName(owner);
                if (owner == "" ){
                    activity.setOwnerName(userRepository.getEmailByArtworkID(resultSet.getInt("ArtworkID")));
                }
                activity.setUserInteract(UserInteraction);
                if (UserInteraction == "") {
                    activity.setUserInteract(userRepository.getEmailByUserID(resultSet.getInt("UserID")));
                }
                activityList.add(activity);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return activityList;
    }
}



