package com.fiit.aass.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fiit.aass.entity.Role;
import com.fiit.aass.repository.RoleDao;

@Service
public class RoleService {

	public void saveRole(Role role) {
		RoleDao.saveRole(role);
	}
	
	public void saveProjectRole(Integer roleId, Integer projectId) {
		RoleDao.saveProjectRole(roleId, projectId);
	}
	
	public List<Role> getAllRoles() {
		return RoleDao.getAllRoles();
	}
	
	public List<String> getRolesNames(List<Role> roles) {
		List<String> rolesNames = new ArrayList<String>();
		for(int i = 0; i < roles.size(); i++) {
			rolesNames.add(i + ". " + roles.get(i).getDescription());
		} 
		return rolesNames;
	}
	
	public List<Role> getProjectRoles(Integer projectId) {
		return RoleDao.getRolesForProject(projectId);
	}
}
