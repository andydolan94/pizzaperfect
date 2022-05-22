package com.andydolan94.pizzaperfect.services;

import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.exceptions.BadResourceException;
import com.andydolan94.pizzaperfect.exceptions.ResourceAlreadyExistsException;
import com.andydolan94.pizzaperfect.exceptions.ResourceNotFoundException;
import com.andydolan94.pizzaperfect.repositories.PizzaOrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PizzaOrderService {

	private PizzaOrderRepository pizzaOrderRepository;

	/**
	 * Checks if an order exists in the database
	 * @param id the id of the order
	 * @return the boolean indicating if the order exists or not
	 */
	private boolean existsById(Long id) {
		return pizzaOrderRepository.existsById(id);
	}

	/**
	 * Class constructor defining its associated repository
	 * @param pizzaRepository the repository
	 */
	public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
		this.pizzaOrderRepository = pizzaOrderRepository;
	}

	/**
	 * Lists all of the orders in the database
	 * @return the list of orders
	 */
	public List<PizzaOrder> findAll() {
		return pizzaOrderRepository.findAll();
	}

	/**
	 * Finds an order given a supplied id
	 * @param id the id of the order
	 * @return the order
	 * @throws ResourceNotFoundException if the order cannot be found
	 */
	public Optional<PizzaOrder> findById(long id)
		throws ResourceNotFoundException {
		if (!existsById(id)) {
			throw new ResourceNotFoundException(
				"Cannot find order with id: %d".formatted(id)
			);
		} else {
			return pizzaOrderRepository.findById(id);
		}
	}

	/**
	 * Saves an order in the database
	 * @param pizzaOrder the order to save
	 * @return the saved order
	 * @throws BadResourceException if the order given is malformed
	 * @throws ResourceAlreadyExistsException if the order given already exists
	 * 										  with the supplied id
	 */
	public PizzaOrder save(PizzaOrder pizzaOrder)
		throws BadResourceException, ResourceAlreadyExistsException {
		if (
			StringUtils.hasText(pizzaOrder.getCustomerName()) &&
			StringUtils.hasText(pizzaOrder.getDeliveryAddress())
		) {
			if (existsById(pizzaOrder.getId())) {
				throw new ResourceAlreadyExistsException(
					"Order with id: %d already exists".formatted(
							pizzaOrder.getId()
						)
				);
			}
			return pizzaOrderRepository.save(pizzaOrder);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save order"
			);
			ex.addErrorMessage("Order is null or empty");
			throw ex;
		}
	}

	/**
	 * Updates an existing order with the matching id
	 * @param pizzaOrder the order to be updated
	 * @return the updated order
	 * @throws BadResourceException if the updated order is malformed
	 * @throws ResourceNotFoundException if the existing order cannot be found
	 */
	public PizzaOrder update(PizzaOrder pizzaOrder)
		throws BadResourceException, ResourceNotFoundException {
		if (
			StringUtils.hasText(pizzaOrder.getCustomerName()) &&
			StringUtils.hasText(pizzaOrder.getDeliveryAddress())
		) {
			if (!existsById(pizzaOrder.getId())) {
				throw new ResourceNotFoundException(
					"Cannot find order with id: %d".formatted(
							pizzaOrder.getId()
						)
				);
			}
			return pizzaOrderRepository.save(pizzaOrder);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save order"
			);
			ex.addErrorMessage("Order is null or empty");
			throw ex;
		}
	}
}
