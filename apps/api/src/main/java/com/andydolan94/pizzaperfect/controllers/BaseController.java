package com.andydolan94.pizzaperfect.controllers;

import com.andydolan94.pizzaperfect.entities.Base;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.services.BaseService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseController {
	
	@Autowired
	private BaseService baseService;

	/**
	 * Gets a list of bases
	 * @return a response with the list of bases
	 */
	@GetMapping("/bases")
	public ResponseEntity<List<Base>> getAllBase() {
		return new ResponseEntity<>(baseService.findAll(), HttpStatus.OK);
	}

	/**
	 * Gets a single base
	 * @param id the base id
	 * @return a response with the base
	 */
	@GetMapping("/bases/{id}")
	public ResponseEntity<Optional<Base>> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
				baseService.findById(id),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates a base
	 * @param base the base to be created
	 * @return a response with the base
	 */
	@PostMapping("/bases")
	public ResponseEntity<Base> create(
		@RequestBody Base base
	) {
		try {
			return new ResponseEntity<>(
				baseService.save(base),
				HttpStatus.CREATED
			);
		} catch (ResourceAlreadyExistsException ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Updates a base
	 * @param id the id of the existing base
	 * @param base the base to replace the existing base
	 * @return a response with the updated base
	 */
	@PutMapping("/bases/{id}")
	public ResponseEntity<Base> update(
		@PathVariable Long id,
		@RequestBody Base base
	) {
		try {
			base.setId(id); // Overwrite the id if one is supplied in the body
			return new ResponseEntity<>(
				baseService.update(base),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
