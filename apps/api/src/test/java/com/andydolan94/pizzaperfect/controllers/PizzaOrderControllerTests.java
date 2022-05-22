package com.andydolan94.pizzaperfect.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.services.PizzaOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PizzaOrderController.class)
public class PizzaOrderControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private PizzaOrderService pizzaOrderService;

	/**
	 * Assembles a list of 2 orders, returns them through a mock request, and
	 * checks if 2 orders have been added.
	 * @throws Exception if the order list cannot be found
	 */
	@Test
	void shouldGetAllThePizzaOrders() throws Exception {
		List<PizzaOrder> pizzaOrderList = new ArrayList<>();
		pizzaOrderList.add(
			new PizzaOrder(
				1L,
				"John Doe",
				"1 example lane, testown, presetville 1234"
			)
		);
		pizzaOrderList.add(
			new PizzaOrder(
				2L,
				"Random Citizen",
				"2 interesting drive, upward, backward 2468"
			)
		);

		when(pizzaOrderService.findAll()).thenReturn(pizzaOrderList);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.get("/pizza-orders")
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$", hasSize(2)))
			.andDo(print());
	}

	/**
	 * Assembles an order, returns it through a mock request, then checks if
	 * the response is ok (200), and if the id and note matches.
	 * @throws Exception if the order cannot be retrieved
	 */
	@Test
	void shouldGetOnePizzaOrder() throws Exception {
		long id = 1L;

		PizzaOrder pizzaOrder = new PizzaOrder(
			id,
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);

		when(pizzaOrderService.findById(id))
			.thenReturn(Optional.of(pizzaOrder));

		mockMvc
			.perform(MockMvcRequestBuilders.get("/pizza-orders/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(
				jsonPath("$.customerName").value(pizzaOrder.getCustomerName())
			)
			.andExpect(
				jsonPath("$.deliveryAddress")
					.value(pizzaOrder.getDeliveryAddress())
			)
			.andDo(print());
	}

	/**
	 * Assembles an order, creates it through a mock request, checks if the
	 * response shows it has been created (201), and if the id, the customer
	 * name and the delivery address matches.
	 * @throws Exception if the order cannot be created
	 */
	@Test
	void shouldSuccessfullyCreateAPizzaOrder() throws Exception {
		long id = 1L;
		PizzaOrder pizzaOrder = new PizzaOrder(
			id,
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);

		when(pizzaOrderService.save(any(PizzaOrder.class)))
			.thenReturn(pizzaOrder);

		ObjectMapper objectMapper = new ObjectMapper();
		String pizzaOrderJSON = objectMapper.writeValueAsString(pizzaOrder);

		ResultActions result = mockMvc.perform(
			post("/pizza-orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pizzaOrderJSON)
		);

		result
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.customerName").value("John Doe"))
			.andExpect(
				jsonPath("$.deliveryAddress")
					.value("1 example lane, testown, presetville 1234")
			);
	}

	/**
	 * Assemble 2 orders, return the first one through a mock request, update
	 * the second using another mock request, then check if the id, the customer
	 * name, and the delivery address all match.
	 * @throws Exception if the pizza cannot be updated
	 */
	@Test
	void shouldSuccessfullyUpdateAPizzaOrder() throws Exception {
		long id = 1L;
		PizzaOrder pizzaOrder = new PizzaOrder(
			id,
			"John Doe",
			"1 example lane, testown, presetville 1234"
		);
		PizzaOrder newPizzaOrder = new PizzaOrder(
			id,
			"Random Citizen",
			"2 interesting drive, upward, backward 2468"
		);

		when(pizzaOrderService.findById(id))
			.thenReturn(Optional.of(pizzaOrder));
		when(pizzaOrderService.update(any(PizzaOrder.class)))
			.thenReturn(newPizzaOrder);

		ObjectMapper objectMapper = new ObjectMapper();
		String newPizzaOrderJSON = objectMapper.writeValueAsString(
			newPizzaOrder
		);

		ResultActions result = mockMvc.perform(
			put("/pizza-orders/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(newPizzaOrderJSON)
		);

		result
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customerName").value("Random Citizen"))
			.andExpect(
				jsonPath("$.deliveryAddress")
					.value("2 interesting drive, upward, backward 2468")
			);
	}
}
