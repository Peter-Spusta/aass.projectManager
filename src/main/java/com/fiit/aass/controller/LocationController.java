package com.fiit.aass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiit.aass.entity.Location;
import com.fiit.aass.entity.Project;
import com.fiit.aass.repository.LocationRepository;

@Component
@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationRepository locationRepository;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<Location>> getSeatingForProject() {
		List<Location> locations = locationRepository.findAll();

		return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
	}
}
