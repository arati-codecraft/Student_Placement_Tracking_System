package org.placepro.dao;

import java.util.List;

import org.placepro.model.Company;

public interface CompanyDao {
    void addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(int id);
    List<Company> getAllCompanies();
    Company getCompanyById(int id);
}