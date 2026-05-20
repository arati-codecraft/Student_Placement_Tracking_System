package org.placepro.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.placepro.DBconfig.DBConnection;
import org.placepro.model.Users;
import org.placepro.repository.UsersRepository;

public class UsersRepositoryImpl implements UsersRepository {

    @Override
    public boolean save(Users user) {
        String sql = "INSERT INTO users(name,email,mobile,course,percentage,skills,password,role,status) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getCourse());
            ps.setDouble(5, user.getPercentage());
            ps.setString(6, user.getSkills());
            ps.setString(7, user.getPassword());
            ps.setString(8, user.getRole());
            ps.setString(9, user.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Users user) {
        String sql = "UPDATE users SET name=?, email=?, mobile=?, course=?, percentage=?, skills=?, password=?, role=?, status=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getCourse());
            ps.setDouble(5, user.getPercentage());
            ps.setString(6, user.getSkills());
            ps.setString(7, user.getPassword());
            ps.setString(8, user.getRole());
            ps.setString(9, user.getStatus());
            ps.setInt(10, user.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }

        // 🔑 Keep placements table in sync
        String placementSql = "UPDATE placements SET status=? WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(placementSql)) {
            ps.setString(1, user.getStatus());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void updateUser(Users user) {
        // ✅ Simply delegate to update()
        update(user);
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public Users findById(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public Users getUserById(int id) {
        // ✅ Same as findById
        return findById(id);
    }

    @Override
    public List<Users> findAll() {
        List<Users> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Users> findAllStudents() {
        List<Users> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE role='Student'");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Users findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapUser(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public void updateStatus(int id, String status) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE users SET status=? WHERE id=?")) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<Users> search(String course, Double percentage, String skill) {
        List<Users> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE 1=1");
        if (course != null) {
			sql.append(" AND course=?");
		}
        if (percentage != null) {
			sql.append(" AND percentage>=?");
		}
        if (skill != null) {
			sql.append(" AND skills LIKE ?");
		}
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            if (course != null) {
				ps.setString(idx++, course);
			}
            if (percentage != null) {
				ps.setDouble(idx++, percentage);
			}
            if (skill != null) {
				ps.setString(idx++, "%" + skill + "%");
			}
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    private Users mapUser(ResultSet rs) throws SQLException {
        Users u = new Users();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setMobile(rs.getString("mobile"));
        u.setCourse(rs.getString("course"));
        u.setPercentage(rs.getDouble("percentage"));
        u.setSkills(rs.getString("skills"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("role"));
        u.setStatus(rs.getString("status"));
        return u;
    }
}
