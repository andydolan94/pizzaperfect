package com.andydolan94.pizzaperfect.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andydolan94.pizzaperfect.entities.Base;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.BaseRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseServiceTests {

	@Autowired
	private BaseRepository baseRepository;

	@AfterEach
	void tearDown() {
		baseRepository.deleteAll();
	}

	/**
	 * Arrange a base and save it to the database, retrieve all bases
	 * and check if the id, name, and description matches.
	 */
	@Test
	void shouldGetAllBases() {
		// Arrange
		Base baseSample = new Base(
			"Deep Dish",
			"Classic soft dough bread!"
		);
		baseRepository.save(baseSample);
		BaseService baseService = new BaseService(
			baseRepository
		);

		// Act
		Base firstResult = baseService.findAll().get(0);

		// Assert
		assertEquals(baseSample.getId(), firstResult.getId());
		assertEquals(
			baseSample.getName(),
			firstResult.getName()
		);
		assertEquals(
			baseSample.getDescription(),
			firstResult.getDescription()
		);
	}

	/**
	 * Arrange a base and save it to the database, retrieve that base
	 * and check if the id and note matches.
	 * @throws ResourceNotFoundException if the base cannot be found
	 */
	@Test
	void shouldGetOneBase() throws ResourceNotFoundException {

		// Arrange
		Base baseSample = new Base(
			"Deep Dish",
			"Classic soft dough bread!"
		);
		long id = baseRepository.save(baseSample).getId();
		BaseService baseService = new BaseService(
			baseRepository
		);

		// Act
		Base firstResult = baseService.findById(id).get();

		// Assert
		assertEquals(baseSample.getId(), firstResult.getId());
		assertEquals(
			baseSample.getName(),
			firstResult.getName()
		);
		assertEquals(
			baseSample.getDescription(),
			firstResult.getDescription()
		);
	}
}
