package org.placepro.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Placement {

	 private int id;
	    private String studentName;
	    private String email;
	    private String course;
	    private String education;
	    private String companyName;
	    private String location;
	    private double packageAmount;
	    private String status;
}