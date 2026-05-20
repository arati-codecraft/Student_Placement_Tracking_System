package org.placepro.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.placepro.DBconfig.DBConnection;
import org.placepro.model.Notification;
import org.placepro.repository.NotificationRepository;

public class NotificationRepositoryImpl implements NotificationRepository {

    @Override
    public void addNotification(int placementId, String status) {

        String sql =
            "INSERT INTO notifications (student_id, message) " +
            "SELECT p.student_id, CONCAT('Your application for ', c.company_name, ' is ', ?) " +
            "FROM placements p JOIN company c ON p.company_id = c.id " +
            "WHERE p.id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, placementId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> getByStudentId(int studentId) {

        List<Notification> list = new ArrayList<>();

        String sql = "SELECT * FROM notifications WHERE student_id=? ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Notification n = new Notification();
                n.setId(rs.getInt("id"));
                n.setStudentId(rs.getInt("student_id"));
                n.setMessage(rs.getString("message"));
                n.setCreatedAt(rs.getString("created_at"));

                list.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}