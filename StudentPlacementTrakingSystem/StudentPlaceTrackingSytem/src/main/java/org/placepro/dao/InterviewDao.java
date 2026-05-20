package org.placepro.dao;

import java.util.List;

import org.placepro.model.Interview;

public interface InterviewDao {

    void scheduleInterview(Interview interview);

    List<Interview> getStudentInterviews(int studentId);
}