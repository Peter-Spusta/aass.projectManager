package com.fiit.aass.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = true, length = 45)
	private String name;
	 
	@Column(name = "description", nullable = true, length = 511)
	private String description;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<Role> rsToEntity(ResultSet rs) throws SQLException {
		List<Role> roles = new ArrayList<Role>();
		
		while(rs.next()) {
			Role role = new Role();
			role.setId(rs.getInt(1));
			role.setDescription(rs.getString(2));
			role.setName(rs.getString(3));
			roles.add(role);
		}
		return roles;
	}
}
