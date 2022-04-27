package com.fiit.aass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fiit.aass.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value="select r.id, r.name, r.description from role as r JOIN project_role as pr on r.id = pr.idrole where pr.idproject = ?1", nativeQuery = true)
	List<Role> getRolesForProject(Integer id);
	
	@Query(value="select r.id, r.name, r.description from role as r JOIN employee_role as pr on r.id = pr.idrole where pr.idemployee = ?1", nativeQuery = true)
	List<Role> getRolesForEmployee(Integer id);
}
