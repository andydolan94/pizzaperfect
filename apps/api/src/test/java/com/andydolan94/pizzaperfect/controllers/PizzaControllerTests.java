package com.andydolan94.pizzaperfect.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andydolan94.pizzaperfect.entities.Pizza;
import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import com.andydolan94.pizzaperfect.services.PizzaService;
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
@WebMvcTest(PizzaController.class)
public class PizzaControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private PizzaService pizzaService;

	/**
	 * Assembles a list of 2 pizzas, returns them through a mock request, and
	 * checks if 2 pizzas have been added.
	 * @throws Exception if the pizza list cannot be found
	 */
	@Test
	void shouldGetAllThePizzas() throws Exception {
		long pizzaOrderId = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234",
			true,
			false
		)
			.getId();
		List<Pizza> pizzaList = new ArrayList<>();
		pizzaList.add(
			new Pizza(
				1L,
				"Hawaiian",
				"Deep Dish",
				"Regular",
				"Please add extra olives",
				pizzaOrderId
			)
		);
		pizzaList.add(
			new Pizza(
				2L,
				"Pepperoni",
				"Thin Crust",
				"Large",
				"Please add extra anchovies",
				pizzaOrderId
			)
		);

		when(pizzaService.findAll()).thenReturn(pizzaList);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.get("/pizzas")
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$", hasSize(2)))
			.andDo(print());
	}

	/**
	 * Assembles a pizza, returns it through a mock request, then checks if
	 * the response is ok (200), and if the id, topping, base, size, and note matches.
	 * @throws Exception if the pizza cannot be retrieved
	 */
	@Test
	void shouldGetOnePizza() throws Exception {
		long id = 1L;

		long pizzaOrderId = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234",
			true,
			false
		)
			.getId();
		Pizza pizza = new Pizza(
			id,
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);

		when(pizzaService.findById(id)).thenReturn(Optional.of(pizza));

		mockMvc
			.perform(MockMvcRequestBuilders.get("/pizzas/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.topping").value(pizza.getTopping()))
			.andExpect(jsonPath("$.base").value(pizza.getBase()))
			.andExpect(jsonPath("$.size").value(pizza.getSize()))
			.andExpect(jsonPath("$.note").value(pizza.getNote()))
			.andDo(print());
	}

	/**
	 * Assembles a pizza, creates it through a mock request, checks if the
	 * response shows it has been created (201), and if the id, topping,
	 * base, size, and note matches.
	 * @throws Exception if the pizza cannot be created
	 */
	@Test
	void shouldSuccessfullyCreateAPizza() throws Exception {
		long id = 1L;
		long pizzaOrderId = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234",
			true,
			false
		)
			.getId();
		Pizza pizza = new Pizza(
			id,
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);

		when(pizzaService.save(any(Pizza.class))).thenReturn(pizza);

		ObjectMapper objectMapper = new ObjectMapper();
		String pizzaJSON = objectMapper.writeValueAsString(pizza);

		ResultActions result = mockMvc.perform(
			post("/pizzas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pizzaJSON)
		);

		result
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.topping").value(pizza.getTopping()))
			.andExpect(jsonPath("$.base").value(pizza.getBase()))
			.andExpect(jsonPath("$.size").value(pizza.getSize()))
			.andExpect(jsonPath("$.note").value(pizza.getNote()));
	}

	/**
	 * Assemble 2 pizzas, return the first one through a mock request, update
	 * the second using another mock request, then check if the id, topping, base, size
	 * and note matches.
	 * @throws Exception if the pizza cannot be updated
	 */
	@Test
	void shouldSuccessfullyUpdateAPizza() throws Exception {
		long id = 1L;
		long pizzaOrderId = new PizzaOrder(
			"John Doe",
			"1 example lane, testown, presetville 1234",
			true,
			false
		)
			.getId();
		Pizza pizza = new Pizza(
			id,
			"Hawaiian",
			"Deep Dish",
			"Regular",
			"Please add extra olives",
			pizzaOrderId
		);
		Pizza newPizza = new Pizza(
			id,
			"Pepperoni",
			"Thin Crust",
			"Large",
			"Please add extra anchovies",
			pizzaOrderId
		);

		when(pizzaService.findById(id)).thenReturn(Optional.of(pizza));
		when(pizzaService.update(any(Pizza.class))).thenReturn(newPizza);

		ObjectMapper objectMapper = new ObjectMapper();
		String newPizzaJSON = objectMapper.writeValueAsString(newPizza);

		ResultActions result = mockMvc.perform(
			put("/pizzas/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(newPizzaJSON)
		);

		result
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.topping").value(newPizza.getTopping()))
			.andExpect(jsonPath("$.base").value(newPizza.getBase()))
			.andExpect(jsonPath("$.size").value(newPizza.getSize()))
			.andExpect(jsonPath("$.note").value(newPizza.getNote()));
	}
}
