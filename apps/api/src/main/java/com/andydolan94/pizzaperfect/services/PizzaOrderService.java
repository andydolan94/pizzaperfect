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

	private boolean existsById(Long id) {
		return pizzaOrderRepository.existsById(id);
	}

	public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
		this.pizzaOrderRepository = pizzaOrderRepository;
	}

	public List<PizzaOrder> findAll() {
		return pizzaOrderRepository.findAll();
	}

	public Optional<PizzaOrder> findById(long id) throws ResourceNotFoundException {
		if (!existsById(id)) {
			throw new ResourceNotFoundException(
				"Cannot find Contact with id: %d".formatted(
						id
					)
			);
		} else {
			return pizzaOrderRepository.findById(id);
		}
	}

	public PizzaOrder save(PizzaOrder pizzaOrder)
		throws BadResourceException, ResourceAlreadyExistsException {
		if (
			StringUtils.hasText(pizzaOrder.getCustomerName()) &&
			StringUtils.hasText(pizzaOrder.getDeliveryAddress())
		) {
			if (existsById(pizzaOrder.getId())) {
				throw new ResourceAlreadyExistsException(
					"Contact with id: %d already exists".formatted(
							pizzaOrder.getId()
						)
				);
			}
			return pizzaOrderRepository.save(pizzaOrder);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save pizza order"
			);
			ex.addErrorMessage("Pizza order is null or empty");
			throw ex;
		}
	}

	public PizzaOrder update(PizzaOrder pizzaOrder)
		throws BadResourceException, ResourceNotFoundException {
		if (
			StringUtils.hasText(pizzaOrder.getCustomerName()) &&
			StringUtils.hasText(pizzaOrder.getDeliveryAddress())
		) {
			if (!existsById(pizzaOrder.getId())) {
				throw new ResourceNotFoundException(
					"Cannot find Contact with id: %d".formatted(
							pizzaOrder.getId()
						)
				);
			}
			return pizzaOrderRepository.save(pizzaOrder);
		} else {
			BadResourceException ex = new BadResourceException(
				"Failed to save contact"
			);
			ex.addErrorMessage("Contact is null or empty");
			throw ex;
		}
	}
}
