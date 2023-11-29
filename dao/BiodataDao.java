package Pertemuan10.dao;

import Pertemuan10.biodata.Biodata;
import Pertemuan10.db.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiodataDao {
    public int insert(Biodata biodata) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO biodata (nama, no_hp, jk, id_bio, alamat) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getNoHp());
            statement.setString(3, biodata.getJenisKelamin());
            statement.setString(4, biodata.getId());
            statement.setString(5, biodata.getAlamat());

            result = statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Biodata biodata) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("UPDATE biodata SET nama = ?, no_hp = ?, jk = ?, alamat = ? WHERE id_bio = ?");
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getNoHp());
            statement.setString(3, biodata.getJenisKelamin());
            statement.setString(4, biodata.getAlamat());
            statement.setString(5, biodata.getId());

            result = statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(String id) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM biodata WHERE id_bio = ?");
            statement.setString(1, id);

            result = statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM biodata");) {
                while (resultSet.next()) {
                    Biodata biodata = new Biodata();
                    biodata.setId(resultSet.getString("id_bio"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setNoHp(resultSet.getString("no_hp"));
                    biodata.setJenisKelamin(resultSet.getString("jk"));
                    biodata.setAlamat(resultSet.getString("alamat"));
                    list.add(biodata);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
