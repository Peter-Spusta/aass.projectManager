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

import com.fiit.aass.entity.Role;
import com.fiit.aass.entity.Task;
import com.fiit.aass.repository.TaskRepository;

@Component
@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping(value = "/get")
	public ResponseEntity<List<Task>> getTaskByEmployee(@RequestParam Integer id) {
		List<Task> tasks = taskRepository.getTasksForEmployee(id);

		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
}
