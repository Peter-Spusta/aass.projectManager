package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.repository.RoleDao;
import com.fiit.aass.service.EmployeeService;
import com.fiit.aass.service.RoleService;

import javax.swing.JList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AddEmployeeRoleToProjectView extends JFrame {

	/**
	 * Create the frame.
	 */
	public void addEmployeeRoleToProjectView(Integer projectId) {
		RoleService rs = new RoleService();
		List<String> roleNames = rs.getRolesNames(rs.getAllRoles());
		EmployeeService es = new EmployeeService();
		List<String> employees = es.getEmployees(); 
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 736, 463);
		panel.setLayout(null);
		
		JList roleList = new JList(roleNames.toArray());
		roleList.setBounds(35, 50, 300, 300);
		roleList.addMouseListener((MouseListener) new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        System.out.println(roleList.getSelectedIndex());
		    }
		});

		roleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roleList.setBackground(Color.WHITE);
		panel.add(roleList);
		
		JList employeeList = new JList(employees.toArray());
		employeeList.setBounds(350, 50, 300, 300);
		employeeList.setBackground(Color.WHITE);
		panel.add(employeeList);
		
		JLabel roleLbl = new JLabel("Roles:");
		roleLbl.setBounds(35, 10, 122, 13);
		panel.add(roleLbl);
		
		JLabel lblEmployees = new JLabel("Employees:");
		lblEmployees.setBounds(350, 10, 150, 13);
		panel.add(lblEmployees);
		
		JButton btnAddRoleEmployee = new JButton("Add");
		btnAddRoleEmployee.setBounds(565, 375, 85, 21);
		btnAddRoleEmployee.addActionListener(al -> {
			RoleDao.saveProjectRole(roleList.getSelectedIndex()+1, projectId);
			RoleDao.saveEmployeeRole(roleList.getSelectedIndex()+1, employeeList.getSelectedIndex()+1);
			frame.setVisible(false);
			
			ProjectDetailFrame pdf = new ProjectDetailFrame();
            pdf.projectDetailFrame(projectId);
        });
		panel.add(btnAddRoleEmployee);
		
		JButton btnNewRole = new JButton("New Role");
		btnNewRole.setBounds(35, 375, 85, 21);
		btnNewRole.addActionListener(al -> {
			AddRoleView rf = new AddRoleView();
         	rf.addRoleView(projectId, frame);
        });
		frame.getContentPane().setLayout(null);
		panel.add(btnNewRole);
		
		frame.getContentPane().add(panel);
	}

}
