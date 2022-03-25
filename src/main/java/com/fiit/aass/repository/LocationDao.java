package com.fiit.aass.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fiit.aass.entity.Location;

public class LocationDao {

	public static List<Location> executeQuery(String query){  
		List<Location> locations = new ArrayList<Location>();
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/aass","root","admin");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			locations = Location.rsToEntity(rs);
			con.close();  
			return locations;
		} catch(Exception e) { 
			System.out.println(e);
		}  
		
		return null;
	}
	
	public static List<Location> getLocation(){  
		return executeQuery("select * from location");
	}
}
