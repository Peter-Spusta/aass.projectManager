package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.LocationDao;
import com.fiit.aass.repository.LocationRepository;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.service.ProjectService;

import javax.swing.*;

@Component
public class MainView {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private LocationRepository locationRepository;
	
	public void initUi() {

        //Creating the Frame
        JFrame frame = new JFrame("Project Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JButton projectButton = new JButton("Project");
        projectButton.addActionListener(al -> {
        	//openProjectScreen();
        	
        	ProjectListFrame plf = new ProjectListFrame();
        	plf.projectListFrame();
        	//frame.setVisible(false);
        });
        JButton EmployeeButton = new JButton("Employee");
        EmployeeButton.addActionListener(al -> {
        	//openProjectScreen();
        	
        	EmployeeListFrame plf = new EmployeeListFrame();
        	plf.employeeListFrame();
        	//frame.setVisible(false);
        });
        mb.add(projectButton);
        mb.add(EmployeeButton);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
        
        JFrame projectFrame = new JFrame();
        projectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projectFrame.setSize(900, 600);
        
        
    } 
	
}
