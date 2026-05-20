package org.placepro.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.placepro.DBconfig.DBConnection;
import org.placepro.model.Interview;
import org.placepro.repository.InterviewRepository;

public class InterviewRepositoryImpl implements InterviewRepository {

    @Override
    public void scheduleInterview(Interview i) {
        String sql = "INSERT INTO interview " +
                     "(placement_id, interview_date, interview_time, location, mode, meeting_link, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, i.getPlacementId());
            ps.setString(2, i.getInterviewDate());
            ps.setString(3, i.getInterviewTime());
            ps.setString(4, i.getLocation());
            ps.setString(5, i.getMode());
            ps.setString(6, i.getMeetingLink());
            ps.setString(7, i.getStatus() != null ? i.getStatus() : "Scheduled");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Interview> getStudentInterviews(int studentId) {
        List<Interview> list = new ArrayList<>();

        String sql = "SELECT i.*, c.company_name AS companyName " +
                     "FROM interview i " +
                     "JOIN placements p ON i.placement_id = p.id " +
                     "JOIN companies c ON p.company_id = c.id " +
                     "WHERE p.student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapInterview(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int countInterviews(int studentId) {
        String sql = "SELECT COUNT(*) " +
                     "FROM interview i " +
                     "JOIN placements p ON i.placement_id = p.id " +
                     "WHERE p.student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Helper method to map ResultSet → Interview object
    private Interview mapInterview(ResultSet rs) throws SQLException {
        Interview i = new Interview();
        i.setId(rs.getInt("id"));
        i.setPlacementId(rs.getInt("placement_id"));
        i.setInterviewDate(rs.getString("interview_date"));
        i.setInterviewTime(rs.getString("interview_time"));
        i.setLocation(rs.getString("location"));
        i.setMode(rs.getString("mode"));
        i.setMeetingLink(rs.getString("meeting_link"));
        i.setStatus(rs.getString("status"));
        i.setCompanyName(rs.getString("companyName"));
        return i;
    }

    @Override
    public void save(Interview interview) {
        String sql = "INSERT INTO interview (placement_id, interview_date, interview_time, location, mode, meeting_link, status) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, interview.getPlacementId());
            ps.setString(2, interview.getInterviewDate());
            ps.setString(3, interview.getInterviewTime());
            ps.setString(4, interview.getLocation());
            ps.setString(5, interview.getMode());
            ps.setString(6, interview.getMeetingLink());
            ps.setString(7, interview.getStatus() != null ? interview.getStatus() : "Scheduled");

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Interview> findByStudentId(int studentId) {
        List<Interview> list = new ArrayList<>();
        String sql = "SELECT i.*, c.company_name AS companyName " +
                     "FROM interview i " +
                     "JOIN placements p ON i.placement_id = p.id " +
                     "JOIN companies c ON p.company_id = c.id " +
                     "WHERE p.student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapInterview(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Interview> findAll() {
        List<Interview> list = new ArrayList<>();
        String sql = "SELECT i.*, c.company_name AS companyName " +
                     "FROM interview i " +
                     "JOIN placements p ON i.placement_id = p.id " +
                     "JOIN companies c ON p.company_id = c.id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapInterview(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE interview SET status=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM interview WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper to map DB row → Interview object
    private Interview mapInterview1(ResultSet rs) throws SQLException {
        Interview i = new Interview();
        i.setId(rs.getInt("id"));
        i.setPlacementId(rs.getInt("placement_id"));
        i.setInterviewDate(rs.getString("interview_date"));
        i.setInterviewTime(rs.getString("interview_time"));
        i.setLocation(rs.getString("location"));
        i.setMode(rs.getString("mode"));
        i.setMeetingLink(rs.getString("meeting_link"));
        i.setStatus(rs.getString("status"));
        i.setCompanyName(rs.getString("companyName"));
        return i;
    }
    public List<Interview> getInterviewsByStudent(int studentId) {
        List<Interview> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT i.id, i.interview_date, i.interview_time, i.location, i.mode, i.status, " +
                         "c.name AS companyName " +
                         "FROM interview i " +
                         "JOIN placements p ON i.placement_id = p.id " +
                         "JOIN company c ON p.company_id = c.id " +
                         "WHERE p.student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Interview i = new Interview();
                i.setId(rs.getInt("id"));
                i.setInterviewDate(rs.getDate("interview_date").toString());
                i.setInterviewTime(rs.getString("interview_time"));
                i.setLocation(rs.getString("location"));
                i.setMode(rs.getString("mode"));
                i.setStatus(rs.getString("status"));
                i.setCompanyName(rs.getString("companyName"));
                list.add(i);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }


}
