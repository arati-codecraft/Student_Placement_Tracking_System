package org.placepro.dao;



import java.util.List;

import org.placepro.model.Placement;

public interface AdminDao {

    List<Placement> getAllPlacements();

    int totalStudents();
    int totalCompanies();
    int totalApplications();
    int totalSelected();

    void updateStatus(int id, String status);
}