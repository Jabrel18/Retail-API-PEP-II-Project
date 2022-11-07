package com.cognixia.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.controller.UserController;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class MyGymEquipmentListApplicationTests {

	@Autowired
	MockMvc mockMvc;
		
		@Test
		public void testGetAllStudents() throws Exception {
			
			String uri = "/api/user";
			
			MvcResult mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.get(uri)
					.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			
		
	}

}
