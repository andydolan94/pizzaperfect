package com.andydolan94.pizzaperfect.services;

import com.andydolan94.pizzaperfect.entities.Size;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.SizeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SizeService {

	private SizeRepository sizeRepository;

	/**
	 * Checks if a size exists in the database
	 * @param id the id of the size
	 * @return the boolean indicating if the size exists or not
	 */
	private boolean existsById(Long id) {
		return sizeRepository.existsById(id);
	}

	/**
	 * Class constructor defining its associated repository
	 * @param sizeRepository the repository
	 */
	public SizeService(SizeRepository sizeRepository) {
		this.sizeRepository = sizeRepository;
	}

	/**
	 * Lists all of the sizes in the database
	 * @return the list of sizes
	 */
	public List<Size> findAll() {
		return sizeRepository.findAll();
	}

	/**
	 * Finds a size given a supplied id
	 * @param id the id of the size
	 * @return the size
	 * @throws ResourceNotFoundException if the size cannot be found
	 */
	public Optional<Size> findById(long id) throws ResourceNotFoundException {
		if (!existsById(id)) {
			throw new ResourceNotFoundException(
				"Cannot find size with id: %d".formatted(
						id
					)
			);
		} else {
			return sizeRepository.findById(id);
		}
	}

	/**
	 * Saves a size in the database
	 * @param size the size to save
	 * @return the saved size
	 * @throws BadResourceException if the size given is malformed
	 * @throws ResourceAlreadyExistsException if the size given already exists
	 * 										  with the supplied id
	 */
	public Size save(Size size)
		throws BadResourceException, ResourceAlreadyExistsException {
		if (
			StringUtils.hasText(size.getName()) &&
			StringUtils.hasText(size.getDescription())
		) {
			if (existsById(size.getId())) {
				throw new ResourceAlreadyExistsException(
					"Size with id: %d already exists".formatted(
							size.getId()
						)
				);
			}
			return sizeRepository.save(size);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save size"
			);
			ex.addErrorMessage("Size is null or empty");
			throw ex;
		}
	}

	/**
	 * Updates an existing size with the matching id
	 * @param size the size to be updated
	 * @return the updated size
	 * @throws BadResourceException if the updated size is malformed
	 * @throws ResourceNotFoundException if the existing size cannot be found
	 */
	public Size update(Size size)
		throws BadResourceException, ResourceNotFoundException {
		if (
			StringUtils.hasText(size.getName()) &&
			StringUtils.hasText(size.getDescription())
		) {
			if (!existsById(size.getId())) {
				throw new ResourceNotFoundException(
					"Cannot find size with id: %d".formatted(
							size.getId()
						)
				);
			}
			return sizeRepository.save(size);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save size"
			);
			ex.addErrorMessage("Size is null or empty");
			throw ex;
		}
	}
}
