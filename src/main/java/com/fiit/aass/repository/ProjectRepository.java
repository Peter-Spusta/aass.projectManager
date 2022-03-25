package com.fiit.aass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiit.aass.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
}
