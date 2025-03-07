package Arthub.repository.impl;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.entity.TagArt;
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
        String sql = "UPDATE Artworks SET ImageFile where id = ?";
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, Artwork);
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
    public int addArtwork(Artwork artwork) {
        String sql = "Insert into Artworks([ArtworkName],[Description],[Purchasable],[Price],[ImageFile],[UserID],[Status],[DateCreated]) values (?,?,?,?,?,?,?,?)";
        int generatedID = -1;
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, artwork.getArtworkName());
            statement.setString(2, artwork.getDescription());
            statement.setBoolean(3, artwork.isPurchasable());
            statement.setDouble(4, artwork.getPrice());
            statement.setString(5, artwork.getImageFile());
            statement.setInt(6, artwork.getCreatorID());
            statement.setInt(7, artwork.getStatus());
            statement.setString(8, artwork.getDateCreated());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedID = rs.getInt(1);
            }
            rs.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generatedID;
    }


    @Override
    public List<Artwork> getArtworks() {
        List<Artwork> artworks = new ArrayList<Artwork>();
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
                artwork.setViews(resultSet.getInt("Views"));
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
    public List<Artwork> getArtworksByPurchasable(int pageNumber, int pageSize) {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM Artworks WHERE Purchasable = 1 " +
                "ORDER BY ArtworkID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (pageNumber - 1) * pageSize);
            statement.setInt(2, pageSize);
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
                artwork.setComments(resultSet.getInt("Comments"));
                artwork.setLikes(resultSet.getInt("Likes"));
                artwork.setViews(resultSet.getInt("Views"));
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
    public List<Artwork> getArtworksByPurchasableAndNotCreator(int userId, int pageNumber, int pageSize) {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM Artworks WHERE Purchasable = 1 AND UserID != ? " +
                "ORDER BY ArtworkID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, (pageNumber - 1) * pageSize);
            statement.setInt(3, pageSize);
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
                artwork.setComments(resultSet.getInt("Comments"));
                artwork.setLikes(resultSet.getInt("Likes"));
                artwork.setViews(resultSet.getInt("Views"));
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
            throw new    RuntimeException(e);
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
        artwork.setViews(resultSet.getInt("Views"));
        artwork.setComments(resultSet.getInt("Comments"));
        artwork.setDateCreated(resultSet.getString("DateCreated"));
        return artwork;
    }

    @Override
    public List<Artwork> getTop10LikedArtworks() {
        String sql = "SELECT a.ArtworkID, a.UserID, a.ArtworkName, a.Description, " +
                "FORMAT(a.DateCreated, 'yyyy-MM-dd HH:mm:ss') AS DateCreated, a.Likes, " +
                "a.Views, a.Comments, a.Favorites, a.Purchasable, a.Price, a.ImageFile, " +
                "STUFF((SELECT ',' + CAST(ta.TagID AS VARCHAR) " +
                "       FROM TagArt ta WHERE ta.ArtworkID = a.ArtworkID " +
                "       FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, '') AS TagIDs " +
                "FROM Artworks a " +
                "ORDER BY (COALESCE(a.Views, 0) * 0.5 + COALESCE(a.Likes, 0) * 1 " +
                "+ COALESCE(a.Comments, 0) * 1.25 + COALESCE(a.Favorites, 0) * 1.5) DESC " +
                "OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";

        List<Artwork> artworks = new ArrayList<>();
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

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

                String tagIdsString = resultSet.getString("TagIDs");
                if (tagIdsString != null && !tagIdsString.isEmpty()) {
                    List<TagArt> tagArts = new ArrayList<>();
                    for (String tagId : tagIdsString.split(",")) {
                        TagArt tagArt = new TagArt();
                        tagArt.setTagID(Integer.parseInt(tagId.trim()));
                        tagArts.add(tagArt);
                    }
                    artwork.setArtworkTags(tagArts);
                } else {
                    artwork.setArtworkTags(new ArrayList<>());
                }

                artworks.add(artwork);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artworks;
    }


    @Override
    public String findArtworkPictureByArtworkId(int id) {
        String sql = "SELECT ImageFile FROM Artworks WHERE ArtworkID =?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("ImageFile");
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    @Override
    public List<Artwork> getArtworkByAccountId(int id) {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT A.* FROM Artworks A JOIN [User] U ON A.UserID = U.UserID WHERE U.AccountID = ?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
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
                artwork.setComments(resultSet.getInt("Comments"));
                artwork.setLikes(resultSet.getInt("Likes"));
                artwork.setViews(resultSet.getInt("Views"));
                artwork.setDateCreated(resultSet.getString("DateCreated"));
                artworks.add(artwork);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return artworks;
    }

    @Override
    public void deleteArtworkByArtworkId(int artworkId) {
        String sql = "DELETE FROM Artworks WHERE ArtworkID =?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, artworkId);
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
    public void UpdateArtwork(Artwork artwork) throws SQLException {
        if(artwork.getArtworkID() == 0) {
            throw new SQLException("Artwork image file cannot be null");
        }
        String sql = "UPDATE Artworks SET [ArtworkName]=?,[Description]=?, [Purchasable]=?,[Price]=?, [DateCreated]=?";
        String where = " WHERE artworkID = ?" ;
        sql += where;
        try (Connection connection = ConnectUtils.getInstance().openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artwork.getArtworkName());
            statement.setString(2, artwork.getDescription());
            statement.setBoolean(3, artwork.isPurchasable());
            statement.setDouble(4, artwork.getPrice());
            statement.setString(5, artwork.getDateCreated());
            statement.setInt(6, artwork.getArtworkID());
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void incrementViewCount(int artworkId) {
        String sql = "UPDATE Artworks SET Views = Views + 1 WHERE ArtworkID = ?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, artworkId);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Artwork> GetAllArtworksByTagName(String tagName) {
        String sql = "  SELECT A.* FROM Artworks A JOIN TagArt AT ON A.ArtworkID = AT.ArtworkID JOIN Tag T ON AT.TagID = T.TagID WHERE T.TagName =?";
        List<Artwork> artworks = new ArrayList<>();
        try (Connection connection = ConnectUtils.getInstance().openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tagName);
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
                artwork.setViews(resultSet.getInt("Views"));
                artwork.setDateCreated(resultSet.getString("DateCreated"));
                artworks.add(artwork);
            }
            resultSet.close();
            connection.close();
            statement.close();
        return artworks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
