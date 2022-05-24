package com.andydolan94.pizzaperfect.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andydolan94.pizzaperfect.entities.Pizza;
import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.PizzaOrderRepository;
import com.andydolan94.pizzaperfect.repositories.PizzaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PizzaServiceTests {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private PizzaOrderRepository pizzaOrderRepository;

	private long pizzaOrderId;

	@BeforeEach
	void setUp() {
		PizzaOrder pizzaOrder = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);
		// Need to save a mock order for the pizzas to belong to
		pizzaOrderId = pizzaOrderRepository.save(pizzaOrder).getId();
	}

	@AfterEach
	void tearDown() {
		pizzaRepository.deleteAll();
		pizzaOrderRepository.deleteAll();
	}

	/**
	 * Arrange a pizza and save it to the database, retrieve all pizzas
	 * and check if the id, topping, base, size, and note matches.
	 */
	@Test
	void shouldGetAllPizzas() {
		// Arrange
		Pizza pizzaSample = new Pizza(
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);
		pizzaRepository.save(pizzaSample);
		PizzaService pizzaService = new PizzaService(pizzaRepository);

		// Act
		Pizza firstResult = pizzaService.findAll().get(0);

		// Assert
		assertEquals(pizzaSample.getId(), firstResult.getId());
		assertEquals(pizzaSample.getTopping(), firstResult.getTopping());
		assertEquals(pizzaSample.getBase(), firstResult.getBase());
		assertEquals(pizzaSample.getSize(), firstResult.getSize());
		assertEquals(pizzaSample.getNote(), firstResult.getNote());
	}

	/**
	 * Arrange a pizza and save it to the database, retrieve that pizza
	 * and check if the id, topping, base, size, and note matches.
	 * @throws ResourceNotFoundException if the pizza cannot be found
	 */
	@Test
	void shouldGetOnePizza() throws ResourceNotFoundException {
		// Arrange
		Pizza pizzaSample = new Pizza(
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);
		long id = pizzaRepository.save(pizzaSample).getId();
		PizzaService pizzaService = new PizzaService(pizzaRepository);

		// Act
		Pizza firstResult = pizzaService.findById(id).get();

		// Assert
		assertEquals(pizzaSample.getId(), firstResult.getId());
		assertEquals(pizzaSample.getTopping(), firstResult.getTopping());
		assertEquals(pizzaSample.getBase(), firstResult.getBase());
		assertEquals(pizzaSample.getSize(), firstResult.getSize());
		assertEquals(pizzaSample.getNote(), firstResult.getNote());
	}

	/**
	 * Arrange a pizza, save it to the database, then check if there is
	 * exactly one pizza in the database
	 * @throws BadResourceException if the pizza is malformed
	 * @throws ResourceAlreadyExistsException if a pizza exists already with the supplied id
	 * @throws ResourceNotFoundException if the pizza is being assigned to an order that does not exist
	 */
	@Test
	void shouldSaveAPizza()
		throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
		// Arrange
		PizzaService pizzaService = new PizzaService(pizzaRepository);
		Pizza pizzaSample = new Pizza(
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);

		// Act
		pizzaService.save(pizzaSample);

		// Assert
		assertEquals(1.0, pizzaRepository.count());
	}

	/**
	 * Arrange the first pizza, save it to the database and get its id.
	 * Arrange a second pizza to replace the existing pizza
	 * Replace the existing pizza
	 * Retrieve the pizza via the saved id and check if the pizza
	 * matches the second pizza.
	 * @throws BadResourceException
	 * @throws ResourceNotFoundException
	 */
	@Test
	void shouldUpdateAPizza()
		throws BadResourceException, ResourceNotFoundException {
		// Arrange
		Pizza pizzaSample = new Pizza(
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);
		Long pizzaSampleId = pizzaRepository.save(pizzaSample).getId();
		Pizza newPizzaSample = new Pizza(
			pizzaSampleId,
			"Pepperoni",
			"Thin Crust",
			"Large",
			"Please add extra anchovies",
			pizzaOrderId
		);
		PizzaService pizzaService = new PizzaService(pizzaRepository);

		// Act
		Pizza result = pizzaService.update(newPizzaSample);

		// Assert
		assertEquals(newPizzaSample.getId(), result.getId());
		assertEquals(newPizzaSample.getTopping(), result.getTopping());
		assertEquals(newPizzaSample.getBase(), result.getBase());
		assertEquals(newPizzaSample.getSize(), result.getSize());
		assertEquals(newPizzaSample.getNote(), result.getNote());
	}
}
