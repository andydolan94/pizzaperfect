package com.andydolan94.pizzaperfect.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.PizzaOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PizzaOrderServiceTests {

	@Autowired
	private PizzaOrderRepository pizzaOrderRepository;

	@AfterEach
	void tearDown() {
		pizzaOrderRepository.deleteAll();
	}

	/**
	 * Arrange an order and save it to the database, retrieve all orders
	 * and check if the id, customer name, and delivery address matches.
	 */
	@Test
	void shouldGetAllPizzaOrders() {
		// Arrange
		PizzaOrder pizzaOrderSample = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);
		pizzaOrderRepository.save(pizzaOrderSample);
		PizzaOrderService pizzaOrderService = new PizzaOrderService(
			pizzaOrderRepository
		);

		// Act
		PizzaOrder firstResult = pizzaOrderService.findAll().get(0);

		// Assert
		assertEquals(pizzaOrderSample.getId(), firstResult.getId());
		assertEquals(
			pizzaOrderSample.getCustomerName(),
			firstResult.getCustomerName()
		);
		assertEquals(
			pizzaOrderSample.getDeliveryAddress(),
			firstResult.getDeliveryAddress()
		);
	}

	/**
	 * Arrange an order, save it to the database, then check if there is
	 * exactly one order in the database
	 * @throws BadResourceException if the order is malformed
	 * @throws ResourceAlreadyExistsException if a pizza exists already with the supplied id
	 */
	@Test
	void shouldSaveAPizzaOrder()
		throws BadResourceException, ResourceAlreadyExistsException {
		// Arrange
		PizzaOrderService pizzaOrderService = new PizzaOrderService(
			pizzaOrderRepository
		);
		PizzaOrder pizzaOrderSample = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);

		// Act
		pizzaOrderService.save(pizzaOrderSample);

		// Assert
		assertEquals(1.0, pizzaOrderRepository.count());
	}

	/**
	 * Arrange the first order, save it to the database and get its id.
	 * Arrange a second order to replace the existing order
	 * Replace the existing order
	 * Retrieve the order via the saved id and check if the order 
	 * matches the second order.
	 * @throws BadResourceException
	 * @throws ResourceNotFoundException
	 */
	@Test
	void shouldUpdateAPizzaOrder()
		throws BadResourceException, ResourceNotFoundException {
		// Arrange
		PizzaOrder pizzaOrderSample = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);
		Long pizzaOrderSampleId = pizzaOrderRepository
			.save(pizzaOrderSample)
			.getId();
		PizzaOrder newPizzaOrderSample = new PizzaOrder(
			pizzaOrderSampleId,
			"Random Citizen",
			"2 interesting drive, upward, backward 2468"
		);
		PizzaOrderService pizzaOrderService = new PizzaOrderService(
			pizzaOrderRepository
		);

		// Act
		PizzaOrder result = pizzaOrderService.update(newPizzaOrderSample);

		// Assert
		assertEquals(newPizzaOrderSample.getId(), result.getId());
		assertEquals(
			newPizzaOrderSample.getCustomerName(),
			result.getCustomerName()
		);
		assertEquals(
			newPizzaOrderSample.getDeliveryAddress(),
			result.getDeliveryAddress()
		);
	}
}
