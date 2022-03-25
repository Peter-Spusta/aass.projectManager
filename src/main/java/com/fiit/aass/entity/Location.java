package com.fiit.aass.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = true, length = 45)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<Location> rsToEntity(ResultSet rs) throws SQLException {
		List<Location> locations = new ArrayList<Location>();
		
		while(rs.next()) {
			Location location = new Location();
			location.setId(rs.getInt(1));
			location.setName(rs.getString(2));
			locations.add(location);
		}
		
		return locations;
	}
	
}

