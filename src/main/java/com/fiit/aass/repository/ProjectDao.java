package com.fiit.aass.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;

public class ProjectDao {
	
	public static List<Project> executeQuery(String query){  
		List<Project> projects = new ArrayList<Project>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			projects = Project.rsToEntity(rs);
			con.close();  
			return projects;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static List<Project> executeUpdate(String query){  
		List<Project> projects = new ArrayList<Project>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate(query);  
			con.close();  
			return projects;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static List<Project> getProjects(){  
		return executeQuery("select * from project");
	}

	public static Project saveProject(Project project) {
		Integer id = 0;
		try {
			id = executeQuery("SELECT * FROM aass.project order by id desc limit 1").get(0).getId() + 1;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("insert into project value(" + id +",\'"+ project.getName() +"\',"+ project.getLocation() +",\'"+ project.getDescription() +"\',\'"+ now + "\')");
		
		return getProjectById(id);
	}

	public static Project getProjectById(Integer projectId) {
		return executeQuery("select * from project where id = " + projectId).get(0);
	}
	
	public static List<Project> getProjectByEmployee(Integer employeeId) {
		return executeQuery("SELECT distinct(p.id), p.name, p.location, p.description, p.creation_date  FROM aass.project as p \r\n"
				+ "Join aass.project_role as pr on pr.idproject = p.id\r\n"
				+ "Join aass.employee_role as er on er.idrole = pr.idrole\r\n"
				+ "where er.idemployee = " + employeeId);
	}

	public static void updateProject(Project project, int projectId) {
		Integer id = 0;
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("update project set  name = \'"+ project.getName() +"\', location = "+ project.getLocation() +", description = \'"+ project.getDescription() +"\', creation_date = \'"+ now + "\' where id = " + projectId);
		
	}  
}
