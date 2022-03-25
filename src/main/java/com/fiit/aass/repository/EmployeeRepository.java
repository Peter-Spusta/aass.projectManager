package com.fiit.aass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiit.aass.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
