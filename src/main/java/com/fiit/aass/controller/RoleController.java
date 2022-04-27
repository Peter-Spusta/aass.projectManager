package com.fiit.aass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiit.aass.entity.EmployeeRole;
import com.fiit.aass.entity.Project;
import com.fiit.aass.entity.ProjectRole;
import com.fiit.aass.entity.Role;
import com.fiit.aass.repository.EmployeeRoleRepository;
import com.fiit.aass.repository.ProjectRoleRepository;
import com.fiit.aass.repository.RoleRepository;

@Component
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmployeeRoleRepository employeeRoleRepository;
	
	@Autowired
	private ProjectRoleRepository projectRoleRepository;
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<Role>> getRole(@RequestParam Integer id) {
		List<Role> roles = roleRepository.getRolesForProject(id);

		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee/get")
	public ResponseEntity<List<Role>> getRoleByEmployee(@RequestParam Integer id) {
		List<Role> roles = roleRepository.getRolesForEmployee(id);

		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Role>> getRoleAll() {
		List<Role> roles = roleRepository.findAll();

		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee/add")
	public ResponseEntity<String> getRoleEmployee(@RequestParam Integer idEmployee, @RequestParam Integer idRole, @RequestParam Integer idProject) {
		EmployeeRole er = new EmployeeRole();
		er.setIdEmployee(idEmployee);
		er.setIdRole(idRole);
		
		ProjectRole pr = new ProjectRole();
		pr.setIdProject(idProject);
		pr.setIdRole(idRole);
		
		employeeRoleRepository.save(er);
		projectRoleRepository.save(pr);

		return new ResponseEntity<String>("done", HttpStatus.OK);
	}
	
	
}
