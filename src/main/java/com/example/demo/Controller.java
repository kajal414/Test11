package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ServiceClass service;
	
	
	@PostMapping("/addstudent") 
	public Student adstudent(@RequestBody Student stud)throws Exception {
		return service.addStudent(stud);
		
	}
	
	@PostMapping("/displayAllStud")
	public List<Student> displayAllstudent(){
		return service.displayStudent();
	}
	
	@PostMapping("/addsport")
	public Sport addSport(@RequestBody Sport sp) throws Exception {
		return service.addSport(sp);
	}
	
	@PostMapping("/displayAllSport")
	public List<Sport> displayAllsport(){
		return service.displaySport();
	}
	
	@PostMapping("/addparticipate")
	public String addparticipate(@RequestBody Participate2 p2) throws Exception
	{
		return service.addpartici(p2);
	}
	
	@PostMapping("/percentage")
	public String percentage() {
		return service.Percentage();
	}
	
	@PostMapping("/maxParticipate")
	public String maxPsarticipate() {
		return service.MaxParticipate();
	}
}

