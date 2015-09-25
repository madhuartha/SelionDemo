package com.mycompany.test.base;

public class UserData {
	private String email;
	private String password;
	
	// This constructor required for snakeyaml
    public UserData() {}

    public UserData(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
	    return "[testcaseid: " + getEmail().toString() + ", email: "
	      + getPassword().toString() + ", passwrd: ]";
	  }
	

}
