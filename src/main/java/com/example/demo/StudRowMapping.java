package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudRowMapping implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Student stud=new Student();
		stud.setSid(rs.getInt("sid"));
		stud.setSname(rs.getString("sname"));
		return stud;
		
	}

		
}
