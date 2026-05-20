package org.placepro.repository;

import java.util.List;

import org.placepro.model.Placement;

public interface AdminRepository {
    List<Placement> findAllPlacements();
    int countStudents();
    int countCompanies();
    int countApplications();
    int countSelected();
    void updatePlacementStatus(int id, String status);
}
