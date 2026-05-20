package org.placepro.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.placepro.DBconfig.DBConnection;
import org.placepro.model.Users;
import org.placepro.repository.StudentRepository;

public class StudentRepositoryImpl extends DBConnection  implements StudentRepository {

    @Override
    public void save(Users student) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)"
            );
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPassword());
            ps.setString(4, "Student"); // force role as Student
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id=? AND role='Student'");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> findAllStudents() {
        List<Users> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE role='Student'");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Users u = new Users();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public Users findById(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users u = new Users();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setCourse(rs.getString("course"));
                u.setPercentage(rs.getDouble("percentage"));
                u.setSkills(rs.getString("skills"));
                u.setStatus(rs.getString("status"));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public void update(Users student) {
        String sql = "UPDATE users SET name=?, email=?, mobile=?, course=?, percentage=?, skills=?, status=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getMobile());
            ps.setString(4, student.getCourse());
            ps.setDouble(5, student.getPercentage());
            ps.setString(6, student.getSkills());
            ps.setString(7, student.getStatus());
            ps.setInt(8, student.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
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
        if (course != null && !course.isEmpty()) {
			sql.append(" AND course=?");
		}
        if (percentage != null) {
			sql.append(" AND percentage>=?");
		}
        if (skill != null && !skill.isEmpty()) {
			sql.append(" AND skills LIKE ?");
		}

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            if (course != null && !course.isEmpty()) {
				ps.setString(idx++, course);
			}
            if (percentage != null) {
				ps.setDouble(idx++, percentage);
			}
            if (skill != null && !skill.isEmpty()) {
				ps.setString(idx++, "%" + skill + "%");
			}

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setCourse(rs.getString("course"));
                u.setPercentage(rs.getDouble("percentage"));
                u.setSkills(rs.getString("skills"));
                u.setStatus(rs.getString("status"));
                list.add(u);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

}
