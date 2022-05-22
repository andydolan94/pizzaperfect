package com.andydolan94.pizzaperfect.services;

import com.andydolan94.pizzaperfect.entities.Topping;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.ToppingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ToppingService {

	private ToppingRepository toppingRepository;

	/**
	 * Checks if a topping exists in the database
	 * @param id the id of the topping
	 * @return the boolean indicating if the topping exists or not
	 */
	private boolean existsById(Long id) {
		return toppingRepository.existsById(id);
	}

	/**
	 * Class constructor defining its associated repository
	 * @param toppingRepository the repository
	 */
	public ToppingService(ToppingRepository toppingRepository) {
		this.toppingRepository = toppingRepository;
	}

	/**
	 * Lists all of the toppings in the database
	 * @return the list of toppings
	 */
	public List<Topping> findAll() {
		return toppingRepository.findAll();
	}

	/**
	 * Finds a topping given a supplied id
	 * @param id the id of the topping
	 * @return the topping
	 * @throws ResourceNotFoundException if the topping cannot be found
	 */
	public Optional<Topping> findById(long id) throws ResourceNotFoundException {
		if (!existsById(id)) {
			throw new ResourceNotFoundException(
				"Cannot find topping with id: %d".formatted(
						id
					)
			);
		} else {
			return toppingRepository.findById(id);
		}
	}

	/**
	 * Saves a topping in the database
	 * @param topping the topping to save
	 * @return the saved topping
	 * @throws BadResourceException if the topping given is malformed
	 * @throws ResourceAlreadyExistsException if the topping given already exists
	 * 										  with the supplied id
	 */
	public Topping save(Topping topping)
		throws BadResourceException, ResourceAlreadyExistsException {
		if (
			StringUtils.hasText(topping.getName()) &&
			StringUtils.hasText(topping.getDescription())
		) {
			if (existsById(topping.getId())) {
				throw new ResourceAlreadyExistsException(
					"Topping with id: %d already exists".formatted(
							topping.getId()
						)
				);
			}
			return toppingRepository.save(topping);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save topping"
			);
			ex.addErrorMessage("Topping is null or empty");
			throw ex;
		}
	}

	/**
	 * Updates an existing topping with the matching id
	 * @param topping the topping to be updated
	 * @return the updated topping
	 * @throws BadResourceException if the updated topping is malformed
	 * @throws ResourceNotFoundException if the existing topping cannot be found
	 */
	public Topping update(Topping topping)
		throws BadResourceException, ResourceNotFoundException {
		if (
			StringUtils.hasText(topping.getName()) &&
			StringUtils.hasText(topping.getDescription())
		) {
			if (!existsById(topping.getId())) {
				throw new ResourceNotFoundException(
					"Cannot find topping with id: %d".formatted(
							topping.getId()
						)
				);
			}
			return toppingRepository.save(topping);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save topping"
			);
			ex.addErrorMessage("Topping is null or empty");
			throw ex;
		}
	}
}
