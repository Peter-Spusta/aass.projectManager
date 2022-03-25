package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.entity.Task;
import com.fiit.aass.repository.LocationDao;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.repository.TaskDao;
import com.fiit.aass.service.EmployeeService;

public class AddTaskFrame extends JFrame {

	public void addTaskFrame(JFrame employeeDetailFrame, Integer employeeId, Integer taskId) {
		List<Project> projects = ProjectDao.getProjectByEmployee(employeeId);
		List<String> projectNames = new ArrayList<String>();
		for (Project proj : projects) {
			projectNames.add(proj.getName());
		}
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JLabel taskNameLabel = new JLabel("Task Name:");
		taskNameLabel.setBounds(88, 44, 132, 13);
		panel.add(taskNameLabel);
		
		JLabel projLabel = new JLabel("Project:");
		projLabel.setHorizontalAlignment(SwingConstants.LEFT);
		projLabel.setBounds(88, 67, 132, 13);
		panel.add(projLabel);
		
		JLabel lblTaskDescription = new JLabel("Task Description:");
		lblTaskDescription.setBounds(88, 90, 132, 13);
		panel.add(lblTaskDescription);
		
		JTextField projNameTF = new JTextField();
		projNameTF.setBounds(217, 40, 165, 19);
		panel.add(projNameTF);
		projNameTF.setColumns(10);
		
		JComboBox projLocationCB = new JComboBox(projectNames.toArray());
		projLocationCB.setBounds(217, 62, 165, 21);
		panel.add(projLocationCB);
		
		JTextArea taskDescTA = new JTextArea();
		taskDescTA.setLineWrap(true);
		taskDescTA.setWrapStyleWord(true);
		taskDescTA.setBounds(217, 83, 165, 77);
		panel.add(taskDescTA);
		
		String btnTxt = "Add Project";
		if(taskId >= 0)
			btnTxt = "Update";
		
		JButton addTaskBtn = new JButton("Add Task");
		addTaskBtn.setBounds(327, 231, 117, 21);
		addTaskBtn.addActionListener(al -> {
        	Task task = new Task();
        	task.setDescription(taskDescTA.getText());
        	task.setName(projNameTF.getText());
        	task.setIdEmployee(employeeId);
        	task.setIdProject(projects.get(projLocationCB.getSelectedIndex()).getId());
        	if(taskId >= 0) {
        		employeeDetailFrame.setVisible(false);
        		TaskDao.updateTask(task, taskId);
        		EmployeeDetailFrame pdf = new EmployeeDetailFrame();
                pdf.employeeDetailFrame(employeeId, projects.get(projLocationCB.getSelectedIndex()).getId());
        	} else {	
        		EmployeeDetailFrame pdf = new EmployeeDetailFrame();
                pdf.employeeDetailFrame(TaskDao.saveTask(task).getId(), projects.get(projLocationCB.getSelectedIndex()).getId());
        	}

        	frame.setVisible(false);
        });
		panel.add(addTaskBtn);
		
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(panel);
	}

}
