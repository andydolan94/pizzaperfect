package com.andydolan94.pizzaperfect.services;

import com.andydolan94.pizzaperfect.entities.Pizza;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.PizzaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PizzaService {

	private PizzaRepository pizzaRepository;

	/**
	 * Checks if a pizza exists in the database
	 * @param id the id of the pizza
	 * @return the boolean indicating if the pizza exists or not
	 */
	private boolean existsById(Long id) {
		return pizzaRepository.existsById(id);
	}

	/**
	 * Class constructor defining its associated repository
	 * @param pizzaRepository the repository
	 */
	public PizzaService(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	/**
	 * Lists all of the pizzas in the database
	 * @return the list of pizzas
	 */
	public List<Pizza> findAll() {
		return pizzaRepository.findAll();
	}

	/**
	 * Finds a pizza given a supplied id
	 * @param id the id of the pizza
	 * @return the pizza
	 * @throws ResourceNotFoundException if the pizza cannot be found
	 */
	public Optional<Pizza> findById(long id) throws ResourceNotFoundException {
		if (!existsById(id)) {
			throw new ResourceNotFoundException(
				"Cannot find pizza with id: %d".formatted(
						id
					)
			);
		} else {
			return pizzaRepository.findById(id);
		}
	}

	/**
	 * Saves a pizza in the database
	 * @param pizza the pizza to save
	 * @return the saved pizza
	 * @throws BadResourceException if the pizza given is malformed
	 * @throws ResourceAlreadyExistsException if the pizza given already exists
	 * 										  with the supplied id
	 */
	public Pizza save(Pizza pizza)
		throws BadResourceException, ResourceAlreadyExistsException {
		if (
			StringUtils.hasText(pizza.getNote())
		) {
			if (existsById(pizza.getId())) {
				throw new ResourceAlreadyExistsException(
					"Pizza with id: %d already exists".formatted(
							pizza.getId()
						)
				);
			}
			return pizzaRepository.save(pizza);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save pizza"
			);
			ex.addErrorMessage("Pizza is null or empty");
			throw ex;
		}
	}

	/**
	 * Updates an existing pizza with the matching id
	 * @param pizza the pizza to be updated
	 * @return the updated pizza
	 * @throws BadResourceException if the updated pizza is malformed
	 * @throws ResourceNotFoundException if the existing pizza cannot be found
	 */
	public Pizza update(Pizza pizza)
		throws BadResourceException, ResourceNotFoundException {
		if (
			StringUtils.hasText(pizza.getNote())
		) {
			if (!existsById(pizza.getId())) {
				throw new ResourceNotFoundException(
					"Cannot find pizza with id: %d".formatted(
							pizza.getId()
						)
				);
			}
			return pizzaRepository.save(pizza);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save pizza"
			);
			ex.addErrorMessage("Pizza is null or empty");
			throw ex;
		}
	}
}
