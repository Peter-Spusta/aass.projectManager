package com.fiit.aass.service;

import java.util.ArrayList;
import java.util.List;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.EmployeeDao;
import com.fiit.aass.repository.ProjectDao;

public class EmployeeService {

	public List<String> getEmployees() {
		List<Employee> employees = EmployeeDao.getEmployees();
		List<String> employeeNames = new ArrayList<String>();
		for (Employee employee : employees) {
			employeeNames.add(employee.getName());
		}
		return employeeNames;
	}
	
	public Employee saveEmployee(Employee employee) {
		return EmployeeDao.saveEmployee(employee);
	}
	
	public void updateEmployee(Employee employee, Integer id) {
		EmployeeDao.updateEmployee(employee, id);
	}
	
	public Employee getEmployeeById(Integer Id) {
		return EmployeeDao.getEmployeeById(Id);
	}
}
