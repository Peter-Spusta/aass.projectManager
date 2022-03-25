package com.fiit.aass.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.LocationRepository;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
    @PersistenceContext
    private EntityManager em;
	
	public void saveProject (Project project) {
		projectRepository.save(project);
		
	}
	
	public List<Location> getAllLocation() {
		ApplicationContext context = new AnnotationConfigApplicationContext(LocationRepository.class);
		LocationRepository locationRepo = context.getBean(LocationRepository.class);
	    
	    return locationRepo.findAll();
	}
//		SimpleJpaRepository<Location, Serializable> jpaRepository;
//		jpaRepository = new SimpleJpaRepository<Location, Serializable>(
//		    Location.class, em);
//		return jpaRepository.findAll();
//	}
	
	public List<String> getProjects() {
		List<Project> projects = ProjectDao.getProjects();
		List<String> projectsNames = new ArrayList<String>();
		for (Project project : projects) {
			projectsNames.add(project.getName());
		}
		return projectsNames;
	}
	
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}
}
