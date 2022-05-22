package com.andydolan94.pizzaperfect.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andydolan94.pizzaperfect.entities.Topping;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.ToppingRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToppingServiceTests {

	@Autowired
	private ToppingRepository toppingRepository;

	@AfterEach
	void tearDown() {
		toppingRepository.deleteAll();
	}

	/**
	 * Arrange a topping and save it to the database, retrieve all toppings
	 * and check if the id, name, and description matches.
	 */
	@Test
	void shouldGetAllToppings() {
		// Arrange
		Topping toppingSample = new Topping(
			"Hawaiian",
			"Cheese, pineapple, and ham... a classic favourite!"
		);
		toppingRepository.save(toppingSample);
		ToppingService toppingService = new ToppingService(
			toppingRepository
		);

		// Act
		Topping firstResult = toppingService.findAll().get(0);

		// Assert
		assertEquals(toppingSample.getId(), firstResult.getId());
		assertEquals(
			toppingSample.getName(),
			firstResult.getName()
		);
		assertEquals(
			toppingSample.getDescription(),
			firstResult.getDescription()
		);
	}

	/**
	 * Arrange a topping and save it to the database, retrieve that topping
	 * and check if the id and note matches.
	 * @throws ResourceNotFoundException if the topping cannot be found
	 */
	@Test
	void shouldGetOneTopping() throws ResourceNotFoundException {

		// Arrange
		Topping toppingSample = new Topping(
			"Hawaiian",
			"Cheese, pineapple, and ham... a classic favourite!"
		);
		long id = toppingRepository.save(toppingSample).getId();
		ToppingService toppingService = new ToppingService(
			toppingRepository
		);

		// Act
		Topping firstResult = toppingService.findById(id).get();

		// Assert
		assertEquals(toppingSample.getId(), firstResult.getId());
		assertEquals(
			toppingSample.getName(),
			firstResult.getName()
		);
		assertEquals(
			toppingSample.getDescription(),
			firstResult.getDescription()
		);
	}
}
