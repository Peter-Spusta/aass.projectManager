package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.entity.Role;
import com.fiit.aass.repository.EmployeeRoleDao;
import com.fiit.aass.repository.LocationDao;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.repository.RoleDao;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;

public class ProjectDetailFrame extends JFrame {

	public void projectDetailFrame(Integer projectId) {
		
		List<Location> locations = LocationDao.getLocation();
		List<String> roleNames = new ArrayList<String>();
		
		List<Role> roles = RoleDao.getRolesForProject(projectId);
		
		for (int i = 0; i < roles.size(); i++) {
			roleNames.add((i + 1) +". " + roles.get(i).getName());
		}
		
		Project project = ProjectDao.getProjectById(projectId);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JList employeeRoleList = new JList(roleNames.toArray());
		employeeRoleList.setBounds(443, 65, 278, 282);
		panel.add(employeeRoleList);
		
		employeeRoleList.addMouseListener((MouseListener) new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            EmployeeDetailFrame pdf = new EmployeeDetailFrame();
		            pdf.employeeDetailFrame(EmployeeRoleDao.getEmployeeFromRole(roles.get(index).getId()).getIdEmployee(), projectId);
		        } 
		    }
		});
		
		JLabel employeeListLbl = new JLabel("Employees:");
		employeeListLbl.setBounds(433, 36, 140, 24);
		panel.add(employeeListLbl);
		
		JLabel projNameLbl = new JLabel("Project Name:");
		projNameLbl.setBounds(23, 42, 99, 13);
		panel.add(projNameLbl);
		
		JLabel projectName = new JLabel(project.getName());
		projectName.setBounds(174, 42, 217, 13);
		panel.add(projectName);
		
		JLabel projDescriptionLbl = new JLabel("Project Description:");
		projDescriptionLbl.setHorizontalAlignment(SwingConstants.LEFT);
		projDescriptionLbl.setBounds(23, 66, 141, 13);
		panel.add(projDescriptionLbl);
		
		JTextArea projDescTA = new JTextArea(project.getDescription());
		projDescTA.setEnabled(false);
		projDescTA.setEditable(false);
		projDescTA.setBounds(174, 60, 217, 115);
		panel.add(projDescTA);
		
		JLabel projLocationLbl = new JLabel("Project Location:");
		projLocationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		projLocationLbl.setBounds(23, 199, 99, 13);
		panel.add(projLocationLbl);
		
		JLabel projLocation = new JLabel(locations.get(project.getLocation()).getName());
		projLocation.setBounds(174, 199, 217, 13);
		panel.add(projLocation);
		
		JButton projUpdateBtn = new JButton("Update");
		projUpdateBtn.addActionListener(al -> {
			AddProjectFrame mf = new AddProjectFrame();
         	mf.addProjectFrame(projectId, frame);
        });
		
		projUpdateBtn.setBounds(23, 361, 99, 24);
		panel.add(projUpdateBtn);
        
        JButton projAddRoleBtn = new JButton("Add Role");
        projAddRoleBtn.addActionListener(al -> {
			AddEmployeeRoleToProjectView epv = new AddEmployeeRoleToProjectView();
			epv.addEmployeeRoleToProjectView(projectId);
        });
        projAddRoleBtn.setBounds(433, 361, 99, 24);
        panel.add(projAddRoleBtn);
        
        frame.addWindowListener(new WindowAdapter() {
        	   public void windowClosing(WindowEvent evt) {
        	     onExit();
        	   }
        });
        frame.getContentPane().add(panel);
        
        JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Project Details");
        lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewJgoodiesTitle.setBounds(23, 19, 550, 13);
        panel.add(lblNewJgoodiesTitle);
       
	} 
	
	public void onExit() {
    	ProjectListFrame plf = new ProjectListFrame();
    	plf.projectListFrame();
    }
}
