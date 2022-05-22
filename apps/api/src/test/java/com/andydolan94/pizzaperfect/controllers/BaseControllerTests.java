package com.andydolan94.pizzaperfect.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andydolan94.pizzaperfect.entities.Base;
import com.andydolan94.pizzaperfect.services.BaseService;
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
@WebMvcTest(BaseController.class)
public class BaseControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private BaseService baseService;

	/**
	 * Assembles a list of 2 bases, returns them through a mock request, and
	 * checks if 2 bases have been added.
	 * @throws Exception if the base list cannot be found
	 */
	@Test
	void shouldGetAllTheBases() throws Exception {
		List<Base> baseList = new ArrayList<>();
		baseList.add(new Base(1L, "Deep Dish", "Classic soft dough bread!"));
		baseList.add(
			new Base(
				2L,
				"Thin Crust",
				"The crunchiest base you'll ever listen to!"
			)
		);

		when(baseService.findAll()).thenReturn(baseList);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.get("/bases")
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$", hasSize(2)))
			.andDo(print());
	}

	/**
	 * Assembles a base, returns it through a mock request, then checks if
	 * the response is ok (200), and if the id and note matches.
	 * @throws Exception if the base cannot be retrieved
	 */
	@Test
	void shouldGetOneBase() throws Exception {
		long id = 1L;

		Base base = new Base(id, "Deep Dish", "Classic soft dough bread!");

		when(baseService.findById(id)).thenReturn(Optional.of(base));

		mockMvc
			.perform(MockMvcRequestBuilders.get("/bases/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.name").value(base.getName()))
			.andExpect(jsonPath("$.description").value(base.getDescription()))
			.andDo(print());
	}
}
