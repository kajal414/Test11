package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SportRowMapping implements RowMapper<Sport> {

	@Override
	public Sport mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Sport sp=new Sport();
		sp.setSpid(rs.getInt("spid"));
		sp.setSpname(rs.getString("spname"));
		
		return sp;
	}
	
	

}
