package org.placepro.repository;

import java.util.List;

import org.placepro.model.Interview;

public interface InterviewRepository {

    void scheduleInterview(Interview interview);

    List<Interview> getStudentInterviews(int studentId);

    int countInterviews(int studentId);
    void save(Interview interview);
    List<Interview> findByStudentId(int studentId);
    List<Interview> findAll();
    void updateStatus(int id, String status);
    void delete(int id);
}