package com.andydolan94.pizzaperfect.services;

import com.andydolan94.pizzaperfect.entities.Base;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.BaseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BaseService {

	private BaseRepository baseRepository;

	/**
	 * Checks if a base exists in the database
	 * @param id the id of the base
	 * @return the boolean indicating if the base exists or not
	 */
	private boolean existsById(Long id) {
		return baseRepository.existsById(id);
	}

	/**
	 * Class constructor defining its associated repository
	 * @param baseRepository the repository
	 */
	public BaseService(BaseRepository baseRepository) {
		this.baseRepository = baseRepository;
	}

	/**
	 * Lists all of the bases in the database
	 * @return the list of bases
	 */
	public List<Base> findAll() {
		return baseRepository.findAll();
	}

	/**
	 * Finds a base given a supplied id
	 * @param id the id of the base
	 * @return the base
	 * @throws ResourceNotFoundException if the base cannot be found
	 */
	public Optional<Base> findById(long id) throws ResourceNotFoundException {
		if (!existsById(id)) {
			throw new ResourceNotFoundException(
				"Cannot find base with id: %d".formatted(
						id
					)
			);
		} else {
			return baseRepository.findById(id);
		}
	}

	/**
	 * Saves a base in the database
	 * @param base the base to save
	 * @return the saved base
	 * @throws BadResourceException if the base given is malformed
	 * @throws ResourceAlreadyExistsException if the base given already exists
	 * 										  with the supplied id
	 */
	public Base save(Base base)
		throws BadResourceException, ResourceAlreadyExistsException {
		if (
			StringUtils.hasText(base.getName()) &&
			StringUtils.hasText(base.getDescription())
		) {
			if (existsById(base.getId())) {
				throw new ResourceAlreadyExistsException(
					"Base with id: %d already exists".formatted(
							base.getId()
						)
				);
			}
			return baseRepository.save(base);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save base"
			);
			ex.addErrorMessage("Base is null or empty");
			throw ex;
		}
	}

	/**
	 * Updates an existing base with the matching id
	 * @param base the base to be updated
	 * @return the updated base
	 * @throws BadResourceException if the updated base is malformed
	 * @throws ResourceNotFoundException if the existing base cannot be found
	 */
	public Base update(Base base)
		throws BadResourceException, ResourceNotFoundException {
		if (
			StringUtils.hasText(base.getName()) &&
			StringUtils.hasText(base.getDescription())
		) {
			if (!existsById(base.getId())) {
				throw new ResourceNotFoundException(
					"Cannot find base with id: %d".formatted(
							base.getId()
						)
				);
			}
			return baseRepository.save(base);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save base"
			);
			ex.addErrorMessage("Base is null or empty");
			throw ex;
		}
	}
}
