package com.fiit.aass.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = true, length = 45)
	private String name;
	
	@Column(name = "location", nullable = true)
	private Integer location;
	
	@Column(name = "description", nullable = true, length = 511)
	private String description;

	@Column(name = "creation_date", nullable = true)
	private LocalDate creationDate;
	
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

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<Project> rsToEntity(ResultSet rs) throws SQLException {
		List<Project> projects = new ArrayList<Project>();
		
		while(rs.next()) {
			Project project = new Project();
			project.setId(rs.getInt(1));
			project.setName(rs.getString(2));
			project.setLocation(rs.getInt(3));
			project.setDescription(rs.getString(4));
			projects.add(project);
		}
		return projects;
	}
}
