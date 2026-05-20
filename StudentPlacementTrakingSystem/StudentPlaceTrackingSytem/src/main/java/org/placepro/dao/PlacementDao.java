package org.placepro.dao;

import java.util.List;

import org.placepro.model.Placement;

public interface PlacementDao {
    void applyPlacement(int studentId, int companyId, double packageAmount);
    List<Placement> getAllApplications();
    void updateStatus(int id, String status);
    int getTotalCompanies();
    int getTotalApplications(int userId);
    int getSelectedCount(int userId);
    int getInterviewCount(int userId);
	void applyPlacement(int studentId, int companyId);
}
