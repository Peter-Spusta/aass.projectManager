package com.fiit.aass.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.Project;

public class EmployeeDao {

	public static List<Employee> executeQuery(String query){  
		List<Employee> employees = new ArrayList<Employee>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			employees = Employee.rsToEntity(rs);
			con.close();  
			return employees;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static List<Employee> executeUpdate(String query){  
		List<Employee> employees = new ArrayList<Employee>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate(query);  
			con.close();  
			return employees;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static List<Employee> getEmployees(){  
		return executeQuery("select * from employee");
	}

	public static Employee saveEmployee(Employee employee) {
		Integer id = 1;
		try {
			id = executeQuery("SELECT * FROM aass.employee order by id desc limit 1").get(0).getId() + 1;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("insert into employee value(" + id +",\'"+ employee.getName() +"\',"+ employee.getAge() + ")");
		
		return getEmployeeById(id);
	}

	public static Employee getEmployeeById(Integer id) {
		return executeQuery("select * from employee where id = " + id).get(0);
	}

	public static void updateEmployee(Employee employee, int id) {
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("update employee set  name = \'"+ employee.getName() +"\', age = "+ employee.getAge() +  " where id = " + id);
		
	}  
}
