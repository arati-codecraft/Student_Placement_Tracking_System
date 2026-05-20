package org.placepro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Interview {
    private int id;
    private int studentId;       // FK to users table
    private int placementId;     // FK to placements table
    private String interviewDate;
    private String interviewTime;
    private String location;
    private String mode;         // Online / Offline
    private String meetingLink;  // if online
    private String status;       // Scheduled / Completed / Pending
    private String companyName;  // for display
}
