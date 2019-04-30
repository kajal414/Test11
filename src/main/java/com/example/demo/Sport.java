package com.example.demo;

public class Sport {

	private int spid;
	private String spname;
	
	public Sport() {
		super();
	}
	public Sport(int spid, String spname) {
		super();
		this.spid = spid;
		this.spname = spname;
	}
	
	public int getSpid() {
		return spid;
	}
	public void setSpid(int spid) {
		this.spid = spid;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	
	
	
}
