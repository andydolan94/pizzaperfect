package com.andydolan94.pizzaperfect.controllers;

import com.andydolan94.pizzaperfect.entities.Topping;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.services.ToppingService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToppingController {
	
	@Autowired
	private ToppingService toppingService;

	/**
	 * Gets a list of toppings
	 * @return a response with the list of toppings
	 */
	@GetMapping("/toppings")
	public ResponseEntity<List<Topping>> getAll() {
		return new ResponseEntity<>(toppingService.findAll(), HttpStatus.OK);
	}

	/**
	 * Gets a single topping
	 * @param id the topping id
	 * @return a response with the topping
	 */
	@GetMapping("/toppings/{id}")
	public ResponseEntity<Optional<Topping>> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
				toppingService.findById(id),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates a topping
	 * @param topping the topping to be created
	 * @return a response with the topping
	 */
	@PostMapping("/toppings")
	public ResponseEntity<Topping> create(
		@RequestBody Topping topping
	) {
		try {
			return new ResponseEntity<>(
				toppingService.save(topping),
				HttpStatus.CREATED
			);
		} catch (ResourceAlreadyExistsException ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Updates a topping
	 * @param id the id of the existing topping
	 * @param topping the topping to replace the existing topping
	 * @return a response with the updated topping
	 */
	@PutMapping("/toppings/{id}")
	public ResponseEntity<Topping> update(
		@PathVariable Long id,
		@RequestBody Topping topping
	) {
		try {
			topping.setId(id); // Overwrite the id if one is supplied in the body
			return new ResponseEntity<>(
				toppingService.update(topping),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
