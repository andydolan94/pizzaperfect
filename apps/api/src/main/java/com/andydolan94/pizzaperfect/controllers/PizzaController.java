package com.andydolan94.pizzaperfect.controllers;

import com.andydolan94.pizzaperfect.entities.Pizza;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.services.PizzaService;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Pizzas
 */
@RestController
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Gets a list of pizzas
	 * @return a response with the list of pizzas
	 */
	@GetMapping("/pizzas")
	public ResponseEntity<List<Pizza>> getAll() {
		return new ResponseEntity<>(pizzaService.findAll(), HttpStatus.OK);
	}

	/**
	 * Gets a single pizza
	 * @param id the pizza id
	 * @return a response with the pizza
	 */
	@GetMapping("/pizzas/{id}")
	public ResponseEntity<Optional<Pizza>> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
				pizzaService.findById(id),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates a pizza
	 * @param pizza the pizza to be created
	 * @return a response with the pizza
	 */
	@PostMapping("/pizzas")
	public ResponseEntity<Pizza> create(
		@RequestBody Pizza pizza
	) {
		try {
			return new ResponseEntity<>(
				pizzaService.save(pizza),
				HttpStatus.CREATED
			);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Updates a pizza
	 * @param id the id of the existing pizza
	 * @param pizza the pizza to replace the existing pizza
	 * @return a response with the updated pizza
	 */
	@PutMapping("/pizzas/{id}")
	public ResponseEntity<Pizza> update(
		@PathVariable Long id,
		@RequestBody Pizza pizza
	) {
		try {
			pizza.setId(id); // Overwrite the id if one is supplied in the body
			return new ResponseEntity<>(
				pizzaService.update(pizza),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Deletes a pizza from the database
	 * @param id the id of the pizza
	 * @return void
	 */
	@DeleteMapping("/pizzas/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			pizzaService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
