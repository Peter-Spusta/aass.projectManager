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

public class RoleDao {
	public static List<Role> executeQuery(String query){  
		List<Role> roles = new ArrayList<Role>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			roles = Role.rsToEntity(rs);
			con.close();  
			return roles;
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
	
	public static List<Role> getRolesForProject(Integer id){  
		return executeQuery("select r.id, r.name, r.description from role as r JOIN project_role as pr on r.id = pr.idrole where pr.idproject = " + id);
	}
	
	public static List<Role> getAllRoles(){  
		return executeQuery("select * from role");
	}
	
	public static Role saveRole(Role role) {
		Integer id =1;
		try {
			id = executeQuery("SELECT * FROM aass.role order by id desc limit 1").get(0).getId() + 1;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("insert into role value(" + id +",\'"+ role.getName() +"\',\'"+ role.getDescription() +"\')");
		
		return getRoleById(id);
	}
	
	public static Role saveProjectRole(Integer roleId, Integer projectId) {
		Integer id = 1;
		try {
			id = executeQuery("SELECT * FROM aass.project_role order by id desc limit 1").get(0).getId() + 1;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("insert into project_role value(" + id +","+ projectId +","+ roleId +")");
		
		return getRoleById(id);
	}
	
	public static Role saveEmployeeRole(Integer roleId, Integer employeeId) {
		Integer id = 1;
		try {
			id = executeQuery("SELECT * FROM aass.employee_role order by id desc limit 1").get(0).getId() + 1;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		LocalDateTime now = LocalDateTime.now();
		executeUpdate("insert into employee_role value(" + id +","+ employeeId +","+ roleId +")");
		
		return getRoleById(id);
	}
	
	
//	public static Role saveRoleForProject(Role role) {
//		saveRole(role);
//		
//		
//	}
	
	public static Role getRoleById(Integer roleId) {
		return executeQuery("select * from role where id = " + roleId).get(0);
	}
}
