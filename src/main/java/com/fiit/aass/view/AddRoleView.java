package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Role;
import com.fiit.aass.service.RoleService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddRoleView extends JFrame {

	/**
	 * Create the frame.
	 */
	public void addRoleView(Integer ProjectId, JFrame toGoFrame) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JLabel roleNameLbl = new JLabel("Role Name:");
		roleNameLbl.setBounds(64, 49, 108, 13);
		panel.add(roleNameLbl);
		
		JLabel lblRoleDescription = new JLabel("Role Description:");
		lblRoleDescription.setBounds(64, 72, 108, 13);
		panel.add(lblRoleDescription);
		
		JTextField roleNameTF = new JTextField();
		roleNameTF.setBounds(175, 46, 199, 19);
		panel.add(roleNameTF);
		roleNameTF.setColumns(10);
		
		JTextArea roleDescTA = new JTextArea();
		roleDescTA.setBounds(175, 66, 199, 112);
		panel.add(roleDescTA);
		
		JButton addNewRoleBtn = new JButton("Add Role");
		addNewRoleBtn.addActionListener(al -> {
			toGoFrame.setVisible(false);
			RoleService rs = new RoleService();
			Role role = new Role();
			role.setName(roleNameTF.getText());
			role.setDescription(roleDescTA.getText());
			rs.saveRole(role);
			frame.setVisible(false);
			AddEmployeeRoleToProjectView epv = new AddEmployeeRoleToProjectView();
			epv.addEmployeeRoleToProjectView(ProjectId);
        });
		addNewRoleBtn.setBounds(289, 228, 85, 21);
		addNewRoleBtn.addActionListener(al -> {
			
        });
		panel.add(addNewRoleBtn);
		frame.getContentPane().add(panel);
	}

}
