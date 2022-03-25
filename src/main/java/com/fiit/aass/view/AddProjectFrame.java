package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.LocationDao;
import com.fiit.aass.repository.ProjectDao;

import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class AddProjectFrame extends JFrame {

	public void addProjectFrame(int projectId, JFrame projDetailFrame) {
		List<Location> locations = LocationDao.getLocation();
		List<String> locationsNames = new ArrayList<String>();
		for (Location loc : locations) {
			locationsNames.add(loc.getName());
		}
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JLabel projNameLabel = new JLabel("Project Name:");
		projNameLabel.setBounds(88, 44, 132, 13);
		panel.add(projNameLabel);
		
		JLabel projLocationLabel = new JLabel("Project Location:");
		projLocationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		projLocationLabel.setBounds(88, 67, 132, 13);
		panel.add(projLocationLabel);
		
		JLabel lblProjectDescription = new JLabel("Project Description:");
		lblProjectDescription.setBounds(88, 90, 132, 13);
		panel.add(lblProjectDescription);
		
		JTextField projNameTF = new JTextField();
		projNameTF.setBounds(217, 40, 165, 19);
		panel.add(projNameTF);
		projNameTF.setColumns(10);
		
		JComboBox projLocationCB = new JComboBox(locationsNames.toArray());
		projLocationCB.setBounds(217, 62, 165, 21);
		panel.add(projLocationCB);
		
		JTextArea projDescTA = new JTextArea();
		projDescTA.setLineWrap(true);
		projDescTA.setWrapStyleWord(true);
		projDescTA.setBounds(217, 83, 165, 77);
		panel.add(projDescTA);
		
		String btnTxt = "Add Project";
		if(projectId >= 0)
			btnTxt = "Update";
		
		JButton addProjBtn = new JButton(btnTxt);
		addProjBtn.setBounds(327, 231, 117, 21);
		addProjBtn.addActionListener(al -> {
        	Project project = new Project();
        	project.setDescription(projDescTA.getText());
        	project.setName(projNameTF.getText());
        	project.setLocation(projLocationCB.getSelectedIndex());
        	if(projectId >= 0) {
        		projDetailFrame.setVisible(false);
        		ProjectDao.updateProject(project, projectId);
        		ProjectDetailFrame pdf = new ProjectDetailFrame();
                pdf.projectDetailFrame(projectId);
        	} else {	
        		ProjectDetailFrame pdf = new ProjectDetailFrame();
                pdf.projectDetailFrame(ProjectDao.saveProject(project).getId());
        	}

        	frame.setVisible(false);
        });
		panel.add(addProjBtn);
		
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(panel);
	}

	/**
	 * Create the frame.
	 */
	
}
