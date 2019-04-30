package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ParticiRowMapping  implements RowMapper<Participate>{

	@Override
	public Participate mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Participate p=new Participate();
		
		p.setPid(rs.getInt("pid"));
		p.setSpid(rs.getInt("spid"));
		return p;
		
	}
	
	

}
