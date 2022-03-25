package com.fiit.aass.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.fiit.aass.entity.Employee;
import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.entity.Role;
import com.fiit.aass.entity.Task;
import com.fiit.aass.repository.TaskDao;
import com.fiit.aass.service.EmployeeService;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class EmployeeDetailFrame extends JFrame {

	/**
	 * Create the frame.
	 */
	public void employeeDetailFrame(Integer id, Integer projectId) {
		EmployeeService es = new EmployeeService();
		Employee employee = es.getEmployeeById(id);
		List<Task> tasks;
		List<String> tasksNames = new ArrayList<String>();
		
		if (projectId >= 0)
			tasks = TaskDao.getTaskByEmployeeAndProject(id, projectId);
		else tasks = TaskDao.getTaskByEmployee(id);
		
		for (Task task : tasks) {
			tasksNames.add(task.getName());
		}
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		getContentPane().setLayout(null);
		
		JList taskList = new JList(tasksNames.toArray());
		taskList.setBounds(430, 70, 278, 282);
		panel.add(taskList);
		
		taskList.addMouseListener((MouseListener) new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            AddTaskFrame adf = new AddTaskFrame();
		        	adf.addTaskFrame(frame, id, tasks.get(index).getId());
		            frame.setVisible(false);
		        } 
		    }
		});
		
		JLabel employeeListLbl = new JLabel("Tasks;");
		employeeListLbl.setBounds(433, 36, 140, 24);
		panel.add(employeeListLbl);
		
		JLabel empNameLbl = new JLabel("Employee Name:");
		empNameLbl.setBounds(23, 42, 125, 13);
		panel.add(empNameLbl);
		
		JLabel projectName = new JLabel(employee.getName());
		projectName.setBounds(174, 42, 217, 13);
		panel.add(projectName);
		
		JLabel empAgeLbl = new JLabel("Employee Age:");
		empAgeLbl.setHorizontalAlignment(SwingConstants.LEFT);
		empAgeLbl.setBounds(23, 66, 141, 13);
		panel.add(empAgeLbl);
		
		JButton projUpdateBtn = new JButton("Update");
		projUpdateBtn.addActionListener(al -> {
			AddEmployeeFrame mf = new AddEmployeeFrame();
         	mf.addEmployeeFrame(id, frame);
        });
		
		projUpdateBtn.setBounds(23, 361, 99, 24);
		panel.add(projUpdateBtn);
        
        JButton addTaskBtn = new JButton("Add Task");
        addTaskBtn.addActionListener(al -> {
        	AddTaskFrame adf = new AddTaskFrame();
        	adf.addTaskFrame(frame, id, -1);
//			AddEmployeeRoleToProjectView epv = new AddEmployeeRoleToProjectView();
//			epv.addEmployeeRoleToProjectView();
        });
        addTaskBtn.setBounds(433, 361, 99, 24);
        panel.add(addTaskBtn);
        
        frame.addWindowListener(new WindowAdapter() {
        	   public void windowClosing(WindowEvent evt) {
        	     onExit();
        	   }
        });
        frame.getContentPane().add(panel);
        
        JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Employee Details");
        lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewJgoodiesTitle.setBounds(23, 19, 550, 13);
        panel.add(lblNewJgoodiesTitle);
        
        JLabel empAge = new JLabel(employee.getAge().toString());
        empAge.setBounds(174, 65, 217, 13);
        panel.add(empAge);
	}
	
	public void onExit() {
    	EmployeeListFrame plf = new EmployeeListFrame();
    	plf.employeeListFrame();
    }

}
