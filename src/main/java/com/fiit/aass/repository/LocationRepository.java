package com.fiit.aass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fiit.aass.entity.Location;

@Repository
public interface LocationRepository  extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location>  {

}
