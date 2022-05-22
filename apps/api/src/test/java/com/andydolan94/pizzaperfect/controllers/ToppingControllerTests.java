package com.andydolan94.pizzaperfect.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andydolan94.pizzaperfect.entities.Topping;
import com.andydolan94.pizzaperfect.services.ToppingService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ToppingController.class)
public class ToppingControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private ToppingService toppingService;

	/**
	 * Assembles a list of 2 toppings, returns them through a mock request, and
	 * checks if 2 toppings have been added.
	 * @throws Exception if the topping list cannot be found
	 */
	@Test
	void shouldGetAllTheToppings() throws Exception {
		List<Topping> toppingList = new ArrayList<>();
		toppingList.add(
			new Topping(
				1L,
				"Hawaiian",
				"Cheese, pineapple, and ham... a classic favourite!"
			)
		);
		toppingList.add(
			new Topping(
				2L,
				"Pepperoni",
				"Salami and cheese... a combo made in heaven!"
			)
		);

		when(toppingService.findAll()).thenReturn(toppingList);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.get("/toppings")
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$", hasSize(2)))
			.andDo(print());
	}

	/**
	 * Assembles a topping, returns it through a mock request, then checks if
	 * the response is ok (200), and if the id and note matches.
	 * @throws Exception if the topping cannot be retrieved
	 */
	@Test
	void shouldGetOneTopping() throws Exception {
		long id = 1L;

		Topping topping = new Topping(
			id,
			"Hawaiian",
			"Cheese, pineapple, and ham... a classic favourite!"
		);

		when(toppingService.findById(id)).thenReturn(Optional.of(topping));

		mockMvc
			.perform(MockMvcRequestBuilders.get("/toppings/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.name").value(topping.getName()))
			.andExpect(
				jsonPath("$.description").value(topping.getDescription())
			)
			.andDo(print());
	}
}
