package com.fiit.aass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiit.aass.entity.EmployeeRole;
import com.fiit.aass.entity.Role;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {

}
