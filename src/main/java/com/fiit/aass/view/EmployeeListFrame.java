package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.service.EmployeeService;

public class EmployeeListFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public void employeeListFrame() {
		EmployeeService es = new EmployeeService();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JList list = new JList(es.getEmployees().toArray());
		
		list.addMouseListener((MouseListener) new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            EmployeeDetailFrame pdf = new EmployeeDetailFrame();
		            pdf.employeeDetailFrame(index+1, -1);
		            System.out.println(index+1);
		            frame.setVisible(false);
		        } 
		    }
		});

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 59, 687, 360);
		panel.add(list);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 736, 31);
		
		JButton employeeBtn = new JButton("Employee");
		employeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(employeeBtn);
		
		panel.add(menuBar);
		
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(panel);
        
        JButton addProjBtn = new JButton("Add Employee");
        addProjBtn.addActionListener(al -> {
    		AddEmployeeFrame mf = new AddEmployeeFrame();
         	mf.addEmployeeFrame(-1, null);
         	frame.setVisible(false);
        });
    	addProjBtn.setBounds(572, 420, 129, 33);
        panel.add(addProjBtn);
        
        JLabel projectListLbl = new JLabel("Employees:");
        projectListLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        projectListLbl.setBounds(10, 36, 294, 20);
        panel.add(projectListLbl);
		
	}

}
