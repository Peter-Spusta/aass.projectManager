package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.ProjectDao;
import com.fiit.aass.service.ProjectService;

import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class ProjectListFrame extends JFrame {

	public void projectListFrame() {

		ProjectService ps = new ProjectService();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JList list = new JList(ps.getProjects().toArray());
		
		list.addMouseListener((MouseListener) new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            ProjectDetailFrame pdf = new ProjectDetailFrame();
		            pdf.projectDetailFrame(index+1);
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
        
        JButton addProjBtn = new JButton("Add Project");
        addProjBtn.addActionListener(al -> {
    		AddProjectFrame mf = new AddProjectFrame();
         	mf.addProjectFrame(-1, null);
         	frame.setVisible(false);
        });
    	addProjBtn.setBounds(572, 420, 129, 33);
        panel.add(addProjBtn);
        
        JLabel projectListLbl = new JLabel("Projects:");
        projectListLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        projectListLbl.setBounds(10, 36, 294, 20);
        panel.add(projectListLbl);
		
	}
	
}
