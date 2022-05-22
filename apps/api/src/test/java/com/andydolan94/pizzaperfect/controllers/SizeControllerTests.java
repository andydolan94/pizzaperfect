package com.andydolan94.pizzaperfect.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andydolan94.pizzaperfect.entities.Size;
import com.andydolan94.pizzaperfect.services.SizeService;
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
@WebMvcTest(SizeController.class)
public class SizeControllerTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private SizeService sizeService;

	/**
	 * Assembles a list of 2 sizes, returns them through a mock request, and
	 * checks if 2 sizes have been added.
	 * @throws Exception if the size list cannot be found
	 */
	@Test
	void shouldGetAllTheSizes() throws Exception {
		List<Size> sizeList = new ArrayList<>();
		sizeList.add(
			new Size(
				1L,
				"Regular",
				"12 inch diameter"
			)
		);
		sizeList.add(
			new Size(
				2L,
				"Large",
				"14 inch diameter"
			)
		);

		when(sizeService.findAll()).thenReturn(sizeList);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.get("/sizes")
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$", hasSize(2)))
			.andDo(print());
	}

	/**
	 * Assembles a size, returns it through a mock request, then checks if
	 * the response is ok (200), and if the id and note matches.
	 * @throws Exception if the size cannot be retrieved
	 */
	@Test
	void shouldGetOneSize() throws Exception {
		long id = 1L;

		Size size = new Size(
			id,
			"Regular",
			"12 inch diameter"
		);

		when(sizeService.findById(id)).thenReturn(Optional.of(size));

		mockMvc
			.perform(MockMvcRequestBuilders.get("/sizes/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.name").value(size.getName()))
			.andExpect(
				jsonPath("$.description").value(size.getDescription())
			)
			.andDo(print());
	}
}
