package com.fiit.aass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fiit.aass.entity.Role;
import com.fiit.aass.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(value="select * from task as r where r.idemployee = ?1", nativeQuery = true)
	List<Task> getTasksForEmployee(Integer id);

}
