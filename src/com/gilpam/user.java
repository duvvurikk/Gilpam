package com.gilpam;

public class user {
	int id;
	String firstname;
	String lastname;
	String email;
	String usertype;
	String mobilenumber;
	String password;
	
	public user(){
		
	}
	
	public user(String email, String password){
		this.id=id;
		this.email=email;
		this.password=password;
	}
	
	public user(int id, String firstname, String lastname, String email, String usertype, String mobilenumber, String password){
		this.id=id;
		this.firstname=firstname;
		this.lastname=lastname;
		this.mobilenumber=mobilenumber;
		this.email=email;
		this.password=password;
		this.usertype=usertype;
	}
	
	public void setid(int id){
		this.id=id;
	}
	
	public void setemail(String email){
		this.email=email;
	}
	
	public void setpassword(String password){
		this.password=password;
	}
	
	public void setfirstname(String firstname){
		this.firstname=firstname;
	}
	
	public void setlastname(String lastname){
		this.lastname=lastname;
	}
	
	public void setmobilenumber(String mobilenumber){
		this.mobilenumber=mobilenumber;
	}
	
	public void setusertype(String usertype){
		this.usertype=usertype;
	}
	
	public int getid(){
		return this.id;
	}
	public String getemail(){
		return this.email;
	}
	public String getpassword(){
		return this.password;
	}
	public String getfirstname(){
		return this.firstname;
	}
	public String getlastname(){
		return this.lastname;
	}
	public String getusertype(){
		return this.usertype;
	}
	public String getmobilenumber(){
		return this.mobilenumber;
	}

}
