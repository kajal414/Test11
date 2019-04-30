package com.example.demo;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Student addStudent(Student stud)throws Exception  {
		
		String query2="select *from Student";
		List<Student> res=jdbcTemplate.query(query2, new StudRowMapping());
		
		for(Student ss:res) 
		{
			 if(ss.getSname().equals(stud.getSname()))
			 {
				throw  new ExceptionCustom("Exception because studnet name "+stud.getSname()+" is already present");  
			 }
			 
		}
			 String query="insert into Student(sname)values(?)";
				jdbcTemplate.update(query,new Object[] {stud.getSname()});
		
		
		String query1="select *from Student where sname=? ";
		return(Student)jdbcTemplate.queryForObject(query1, new Object[] {stud.getSname()}, new StudRowMapping());
		
	}
	
	public List< Student> displayStudent() {
		
		String query="select *from Student";
		return jdbcTemplate.query(query, new StudRowMapping());
			
	}
	
	public Sport addSport(Sport sp) throws Exception {
		
		String query2="select *from Sport";
		List<Sport> res=jdbcTemplate.query(query2, new SportRowMapping());
		
		for(Sport sss:res) {
			if(sss.getSpname().equals(sp.getSpname())) {
				throw new ExceptionCustom("Exception because sport name "+sp.getSpname()+ " is already present");
			}
		}
		
		String query="insert into Sport(spname) values(?)";
		jdbcTemplate.update(query,new Object[] {sp.getSpname()});
		
		String query1="select *from Sport where spname=?";
		return(Sport)jdbcTemplate.queryForObject(query1, new Object[] {sp.getSpname()}, new SportRowMapping());
		
	}
	
	public List<Sport> displaySport(){
		
		String query="select *from Sport";
		return jdbcTemplate.query(query, new SportRowMapping());
		
	}
	
	public String addpartici(Participate2 p2) throws Exception
	{
		
		boolean stdFlag=false;
		int StdId=0;
		
		String sQuery="Select *from Student";
		String spQuery="Select *from Sport";
		
		List<Student>sList=jdbcTemplate.query(sQuery, new StudRowMapping());
		
		List<Sport>spList=jdbcTemplate.query(spQuery, new SportRowMapping());
		
		
		for(Student std :sList) 
		{
			if(std.getSname().equals(p2.getSname()))
			{
				stdFlag=true;
				StdId=std.getSid();
				break;
			}
			
			
		}
		boolean spFlag=false;
		int spId=0;
		for(Sport sp :spList) 
		{
			if(sp.getSpname().equals(p2.getSpname()))
			{
				spFlag=true;
				spId=sp.getSpid();
				break;
			}
				
			
		}
		
		if(stdFlag && spFlag ) {
			
			String query="insert into Participate values(?,?)";
			jdbcTemplate.update(query, new Object[] {StdId,spId});
			return "Participant is added";	
		}
		else
		{
			if(!stdFlag)
				throw new ExceptionCustom("Exception in student class");
			if(!spFlag)
				throw new ExceptionCustom("Exception in Sport class");
		
		}
		return "failed";
		
	}
	
	public String MaxParticipate() 
	{
		
		String pquery="select *from Participate";
		List<Participate> pList=jdbcTemplate.query(pquery, new ParticiRowMapping());
		Hashtable<Integer,Integer>hash=new Hashtable<>();
		
		for(Participate p:pList) 
		{
			if(hash.containsKey(p.getSpid()))
			{
				int temp=hash.get(p.getSpid());
				hash.put(p.getSpid(), temp+1);
			}
			else 
			{
				hash.put(p.getSpid(), 1);
			}
		}
		Integer maxKey=null;
		Integer maxValue =Integer.MIN_VALUE;
		for(Map.Entry<Integer,Integer> entry : hash.entrySet())
		{
		     if(entry.getValue() > maxValue) 
		     {
		         maxValue = entry.getValue();
		         maxKey = entry.getKey();
		     }
		}
		
		String query2="select *from Sport where spid=?";
		Sport splist=jdbcTemplate.queryForObject(query2,new Object[]{maxKey}, new SportRowMapping());
		return "max participate from "+ splist.getSpname()+"is = "+maxValue;

		
	}

	public String Percentage() 
	{
			String sQuery="select *from Student";
			String pQuery="select *from Participate";
			
			List<Student> slist=jdbcTemplate.query(sQuery, new StudRowMapping());
			List<Participate> plist=jdbcTemplate.query(pQuery, new ParticiRowMapping());
			
			float totalStud=0,totalParticipate=0,perc=0;
			
			for(Student stud:slist)
			{
				totalStud++;
				for(Participate ppt:plist) 
				{
					
					if(stud.getSid()==ppt.getPid()) 
					{
						totalParticipate++;
						break;
						
					}
				}
			}
			 perc=((totalParticipate/totalStud)*100);
			return "participation ="+perc+"%";
		}
	
}
