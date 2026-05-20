package org.placepro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Application {
    private int id;
    private int studentId;
    private int companyId;
    private String applyDate;
    private String status;       // e.g. Pending, Accepted, Rejected
    private String companyName;
    private String location;
    private Double packageAmount;
    private String eligibilityCriteria;
// for display
}
