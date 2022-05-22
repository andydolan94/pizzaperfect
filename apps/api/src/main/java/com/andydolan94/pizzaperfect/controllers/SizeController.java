package com.andydolan94.pizzaperfect.controllers;

import com.andydolan94.pizzaperfect.entities.Size;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.services.SizeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SizeController {
	
	@Autowired
	private SizeService sizeService;

	/**
	 * Gets a list of sizes
	 * @return a response with the list of sizes
	 */
	@GetMapping("/sizes")
	public ResponseEntity<List<Size>> getAll() {
		return new ResponseEntity<>(sizeService.findAll(), HttpStatus.OK);
	}

	/**
	 * Gets a single size
	 * @param id the size id
	 * @return a response with the size
	 */
	@GetMapping("/sizes/{id}")
	public ResponseEntity<Optional<Size>> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
				sizeService.findById(id),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates a size
	 * @param size the size to be created
	 * @return a response with the size
	 */
	@PostMapping("/sizes")
	public ResponseEntity<Size> create(
		@RequestBody Size size
	) {
		try {
			return new ResponseEntity<>(
				sizeService.save(size),
				HttpStatus.CREATED
			);
		} catch (ResourceAlreadyExistsException ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Updates a size
	 * @param id the id of the existing size
	 * @param size the size to replace the existing size
	 * @return a response with the updated size
	 */
	@PutMapping("/sizes/{id}")
	public ResponseEntity<Size> update(
		@PathVariable Long id,
		@RequestBody Size size
	) {
		try {
			size.setId(id); // Overwrite the id if one is supplied in the body
			return new ResponseEntity<>(
				sizeService.update(size),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
