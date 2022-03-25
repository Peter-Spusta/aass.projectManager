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
import com.fiit.aass.repository.LocationDao;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.service.EmployeeService;

public class AddEmployeeFrame extends JFrame {

	/**
	 * Create the frame.
	 */
	public void addEmployeeFrame(int empId, JFrame empDetailFrame) {
		EmployeeService es = new EmployeeService();
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
		
		JLabel EmpNameabel = new JLabel("Employee Name:");
		EmpNameabel.setBounds(88, 44, 132, 13);
		panel.add(EmpNameabel);
		
		JLabel empAgeLabel = new JLabel("Employee Age:");
		empAgeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		empAgeLabel.setBounds(88, 67, 132, 13);
		panel.add(empAgeLabel);
		
		JTextField empNameTF = new JTextField();
		empNameTF.setBounds(217, 40, 165, 19);
		panel.add(empNameTF);
		empNameTF.setColumns(10);
		
		JTextField empAgeTF = new JTextField();
        empAgeTF.setColumns(10);
        empAgeTF.setBounds(217, 64, 165, 19);
        panel.add(empAgeTF);
		
		String btnTxt = "Add Project";
		if(empId >= 0)
			btnTxt = "Update";
		
		JButton addEmpBtn = new JButton("Add Employee");
		addEmpBtn.setBounds(327, 231, 117, 21);
		addEmpBtn.addActionListener(al -> {
        	Employee employee = new Employee();
        	employee.setName(empNameTF.getText());
        	employee.setAge(Integer.parseInt(empAgeTF.getText()));
        	es.saveEmployee(employee);
        	if(empId >= 0) {
        		empDetailFrame.setVisible(false);
        		es.updateEmployee(employee, empId);
        		EmployeeDetailFrame pdf = new EmployeeDetailFrame();
                pdf.employeeDetailFrame(empId, -1);
        	} else {	
        		EmployeeDetailFrame pdf = new EmployeeDetailFrame();
                pdf.employeeDetailFrame(es.saveEmployee(employee).getId(), -1);
        	}

        	frame.setVisible(false);
        });
		panel.add(addEmpBtn);
		
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(panel);
	}

}
