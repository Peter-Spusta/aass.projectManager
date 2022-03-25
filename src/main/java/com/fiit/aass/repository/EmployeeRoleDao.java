package com.fiit.aass.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.EmployeeRole;

public class EmployeeRoleDao {

	public static List<EmployeeRole> executeQuery(String query){  
		List<EmployeeRole> employees = new ArrayList<EmployeeRole>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			employees = EmployeeRole.rsToEntity(rs);
			con.close();  
			return employees;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static EmployeeRole getEmployeeFromRole(int roleId) {
		return executeQuery("select * from employee_role where idrole = " + roleId).get(0);
		
	}
}
