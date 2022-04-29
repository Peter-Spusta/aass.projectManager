package com.fiit.aass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.Project;
import com.fiit.aass.entity.Role;
import com.fiit.aass.repository.EmployeeRepository;

@Component
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Employee>> getRoleAll() {
		List<Employee> employees = employeeRepository.findAll();

		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);

		return new ResponseEntity<String>("done", HttpStatus.OK);
	}
}
