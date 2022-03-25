package com.fiit.aass.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "employee_role")
public class EmployeeRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "idemployee", unique = true, nullable = false)
	private Integer idEmployee;
	
	@Column(name = "idrole", unique = true, nullable = false)
	private Integer idRole;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public static List<EmployeeRole> rsToEntity(ResultSet rs) throws SQLException {
		List<EmployeeRole> roles = new ArrayList<EmployeeRole>();
		
		while(rs.next()) {
			EmployeeRole role = new EmployeeRole();
			role.setId(rs.getInt(1));
			role.setIdEmployee(rs.getInt(2));
			role.setIdRole(rs.getInt(3));
			roles.add(role);
		}
		return roles;
	}
	
}

