package com.fiit.aass.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = true, length = 45)
	private String name;
	 
	@Column(name = "desc", nullable = true, length = 511)
	private String description;
	
	@Column(name = "idproject", unique = true, nullable = false)
	private Integer idProject;
	
	@Column(name = "idemployee", unique = true, nullable = false)
	private Integer idEmployee;
	
	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

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

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public static List<Task> rsToEntity(ResultSet rs) throws SQLException {
		List<Task> tasks = new ArrayList<Task>();
		
		while(rs.next()) {
			Task task = new Task();
			task.setId(rs.getInt(1));
			task.setName(rs.getString(2));
			task.setDescription(rs.getString(3));
			task.setIdProject(rs.getInt(4));
			task.setIdEmployee(rs.getInt(5));
			tasks.add(task);
		}
		return tasks;
	}
	
	
}
