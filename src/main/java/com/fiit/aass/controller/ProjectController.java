package com.fiit.aass.controller;
import com.fiit.aass.entity.*;
import com.fiit.aass.repository.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Project>> getProject() {
		List<Project> projects = projectRepository.findAll();

		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> saveProject(@RequestBody Project project) {
		projectRepository.save(project);

		return new ResponseEntity<String>("done", HttpStatus.OK);
	}

	
}
