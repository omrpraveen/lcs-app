package com.lcs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcs.controller.LCSController;
import com.lcs.dto.LCSRequestDto;
import com.lcs.dto.LCSResponseDto;
import com.lcs.dto.LCSValue;
import com.lcs.service.LCSService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LCSController.class)
@DisplayName("LCS Test")
class LcsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LCSService lcsService;

	@Nested
	@DisplayName("Failiure Test Cases")
	class Failiure {
		@Test
		@DisplayName("Request is not valid")
		void notValidRequest() throws JsonProcessingException, Exception {
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/lcs").content(new ObjectMapper().writeValueAsString(null))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest()).andReturn();
			String response = result.getResponse().getContentAsString();
			assertThat(response).contains("Request is not valid");
			assertThat(response).contains("E1003");
		}

		@Test
		@DisplayName("setOfStrings should not be empty")
		void setOfStringsEmpytCheck() throws JsonProcessingException, Exception {
			LCSRequestDto request = new LCSRequestDto();
			request.setSetOfStrings(Collections.emptyList());
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/lcs").content(new ObjectMapper().writeValueAsString(request))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest()).andReturn();
			String response = result.getResponse().getContentAsString();
			assertThat(response).contains("setOfStrings should not be empty");
			assertThat(response).contains("E1002");
		}

		@Test
		@DisplayName("setOfStrings value should be unique")
		void setOfStringsUniqueValidation() throws JsonProcessingException, Exception {
			LCSRequestDto request = new LCSRequestDto();
			List<LCSValue> value = new ArrayList<>();
			value.add(new LCSValue("comcast"));
			value.add(new LCSValue("comcastic"));
			value.add(new LCSValue("comcastic"));
			value.add(new LCSValue("broadcaster"));
			request.setSetOfStrings(value);
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/lcs").content(new ObjectMapper().writeValueAsString(request))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest()).andReturn();
			String response = result.getResponse().getContentAsString();
			assertThat(response).contains("setOfStrings value should be unique");
			assertThat(response).contains("E1001");
		}

	}

	@Nested
	@DisplayName("Success Test Cases")
	class Success {
		@DisplayName("LCS response for single item")
		@Test
		void lcsResponseValidation() throws JsonProcessingException, Exception {
			LCSRequestDto request = new LCSRequestDto();
			List<LCSValue> value = new ArrayList<>();
			value.add(new LCSValue("comcast"));
			value.add(new LCSValue("comcastic"));
			value.add(new LCSValue("broadcaster"));
			request.setSetOfStrings(value);
			mockMvc.perform(MockMvcRequestBuilders.post("/lcs").content(new ObjectMapper().writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}

		@DisplayName("LCS response for multiple item")
		@Test
		void lcsResponseValidationForMultipleItem() throws JsonProcessingException, Exception {
			LCSRequestDto request = new LCSRequestDto();
			List<LCSValue> value = new ArrayList<>();
			value.add(new LCSValue("comcasttest"));
			value.add(new LCSValue("comcastictest"));
			value.add(new LCSValue("broadcastertest"));
			request.setSetOfStrings(value);
			mockMvc.perform(MockMvcRequestBuilders.post("/lcs").content(new ObjectMapper().writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
	}

}
