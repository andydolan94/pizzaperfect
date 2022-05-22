package com.andydolan94.pizzaperfect.controllers;

import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.services.PizzaOrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PizzaOrderController {

	@Autowired
	private PizzaOrderService PizzaOrderService;

	@GetMapping("/pizza-orders")
	public ResponseEntity<List<PizzaOrder>> getAll() {
		return new ResponseEntity<>(PizzaOrderService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/pizza-orders/{id}")
	public ResponseEntity<Optional<PizzaOrder>> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(
				PizzaOrderService.findById(id),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/pizza-orders")
	public ResponseEntity<PizzaOrder> create(
		@RequestBody PizzaOrder pizzaOrder
	) {
		try {
			return new ResponseEntity<>(
				PizzaOrderService.save(pizzaOrder),
				HttpStatus.CREATED
			);
		} catch (ResourceAlreadyExistsException ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/pizza-orders/{id}")
	public ResponseEntity<PizzaOrder> update(
		@PathVariable Long id,
		@RequestBody PizzaOrder pizzaOrder
	) {
		try {
			pizzaOrder.setId(id); // Overwrite the id if one is supplied in the body
			return new ResponseEntity<>(
				PizzaOrderService.update(pizzaOrder),
				HttpStatus.OK
			);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (BadResourceException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
