package com.fiit.aass.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fiit.aass.entity.Project;
import com.fiit.aass.entity.Role;
import com.fiit.aass.entity.Task;

public class TaskDao {

	public static List<Task> executeQuery(String query){  
		List<Task> tasks = new ArrayList<Task>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			tasks = Task.rsToEntity(rs);
			con.close();  
			return tasks;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static void executeUpdate(String query){  
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate(query);  
			con.close();  
		} catch(Exception e) { 
			System.out.println(e);
		}  
	}
	
	public static Task saveTask(Task task) {
		Integer id =1;
		try {
			id = executeQuery("SELECT * FROM aass.task order by id desc limit 1").get(0).getId() + 1;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("insert into task value(" + id +",\'"+ task.getName() +"\',\'"+ task.getDescription() +"\'," + task.getIdProject() + "," + task.getIdEmployee() +")");
		
		return getTaskById(id);
	}
	
	public static void updateTask(Task task, int taskId) {
		executeUpdate("update aass.task as t set  t.name = \'"+ task.getName() + "\', t.desc = \'"+ task.getDescription() +"\', t.idproject = "+ task.getIdProject() + ", t.idemployee = " + task.getIdEmployee() +" where id = " + taskId);
	}  

	public static Task getTaskById(Integer id) {
		return executeQuery("select * from task where id = " + id).get(0);
	}
	
	public static List<Task> getTaskByEmployee(Integer id) {
		return executeQuery("SELECT * FROM aass.task where idemployee = " + id);
	}
	
	public static List<Task> getTaskByEmployeeAndProject(Integer idEmployee, Integer idProject) {
		return executeQuery("SELECT * FROM aass.task where idemployee = " + idEmployee + " and idproject = " + idProject);
	}
}
