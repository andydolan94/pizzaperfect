package com.andydolan94.pizzaperfect.controllers;

import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.services.PizzaOrderService;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Pizza Orders
 */
@RestController
public class PizzaOrderController {

	@Autowired
	private PizzaOrderService pizzaOrderService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Gets a list of orders
	 * @return a response with the list of orders
	 */
	@GetMapping("/pizza-orders")
	public ResponseEntity<List<PizzaOrder>> getAll() {
		return new ResponseEntity<>(pizzaOrderService.findAll(), HttpStatus.OK);
	}

	/**
	 * Gets a single order
	 * @param id the order id
	 * @return a response with the order
	 */
	@GetMapping("/pizza-orders/{id}")
	public ResponseEntity<Optional<PizzaOrder>> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
				pizzaOrderService.findById(id),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates an order
	 * @param pizzaOrder the order to be created
	 * @return a response with the order
	 */
	@PostMapping("/pizza-orders")
	public ResponseEntity<PizzaOrder> create(
		@RequestBody PizzaOrder pizzaOrder
	) {
		try {
			return new ResponseEntity<>(
				pizzaOrderService.save(pizzaOrder),
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
	 * Updates an order
	 * @param id the id of the existing order
	 * @param pizzaOrder the order to replace the existing order
	 * @return a response with the updated order
	 */
	@PutMapping("/pizza-orders/{id}")
	public ResponseEntity<PizzaOrder> update(
		@PathVariable Long id,
		@RequestBody PizzaOrder pizzaOrder
	) {
		try {
			pizzaOrder.setId(id); // Overwrite the id if one is supplied in the body
			return new ResponseEntity<>(
				pizzaOrderService.update(pizzaOrder),
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
}
