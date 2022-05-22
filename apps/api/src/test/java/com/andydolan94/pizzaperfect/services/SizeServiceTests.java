package com.andydolan94.pizzaperfect.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.andydolan94.pizzaperfect.entities.Size;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.SizeRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SizeServiceTests {

	@Autowired
	private SizeRepository sizeRepository;

	@AfterEach
	void tearDown() {
		sizeRepository.deleteAll();
	}

	/**
	 * Arrange a size and save it to the database, retrieve all sizes
	 * and check if the id, name, and description matches.
	 */
	@Test
	void shouldGetAllSizes() {
		// Arrange
		Size sizeSample = new Size(
			"Regular",
			"12 inch diameter"
		);
		sizeRepository.save(sizeSample);
		SizeService sizeService = new SizeService(
			sizeRepository
		);

		// Act
		Size firstResult = sizeService.findAll().get(0);

		// Assert
		assertEquals(sizeSample.getId(), firstResult.getId());
		assertEquals(
			sizeSample.getName(),
			firstResult.getName()
		);
		assertEquals(
			sizeSample.getDescription(),
			firstResult.getDescription()
		);
	}

	/**
	 * Arrange a size and save it to the database, retrieve that size
	 * and check if the id and note matches.
	 * @throws ResourceNotFoundException if the size cannot be found
	 */
	@Test
	void shouldGetOneSize() throws ResourceNotFoundException {

		// Arrange
		Size sizeSample = new Size(
			"Regular",
			"12 inch diameter"
		);
		long id = sizeRepository.save(sizeSample).getId();
		SizeService sizeService = new SizeService(
			sizeRepository
		);

		// Act
		Size firstResult = sizeService.findById(id).get();

		// Assert
		assertEquals(sizeSample.getId(), firstResult.getId());
		assertEquals(
			sizeSample.getName(),
			firstResult.getName()
		);
		assertEquals(
			sizeSample.getDescription(),
			firstResult.getDescription()
		);
	}
}
