package com.fiit.aass;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.LocationRepository;
import com.fiit.aass.service.ProjectService;
import com.fiit.aass.view.MainView;

@SpringBootApplication(scanBasePackages = {"aass.entity", "com.fiit.aass.controller", "aass.repository", "com.fiit.aass.soap"})
public class ProjectApplication extends JFrame  {
	
//	@Autowired
//	ProjectService projectService;
	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		 
		 applicationContext = SpringApplication.run(ProjectApplication.class, args);
//	        displayAllBeans();
    }
    
    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
	
//    public ProjectApplication() {
//        MainView mainView = new MainView();
//		mainView.initUi();
//    }
//    @Override
//    public void run(String... args) throws Exception {
//		
////		Project project = new Project();
////		
////		project.setDescription("This is our first project.");
////		project.setLocation(1);
////		project.setName("First Project");
////		
////		projectService.saveProject(project);
//    }

}
