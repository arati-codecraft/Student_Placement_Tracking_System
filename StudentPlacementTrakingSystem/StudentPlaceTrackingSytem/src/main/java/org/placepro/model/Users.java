package org.placepro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users
{

	 private int id;
	    private String name;
	    private String email;
	    private String mobile;
	    private String course;
	    private double percentage;
	    private String skills;
	    private String password;
	    private String role;
	    private String status;
	    private String  education;
}
