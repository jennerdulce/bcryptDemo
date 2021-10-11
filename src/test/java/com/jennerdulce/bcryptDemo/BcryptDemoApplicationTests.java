package com.jennerdulce.bcryptDemo;

import com.jennerdulce.bcryptDemo.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class BcryptDemoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void account_test()
	{
		Account sut = new Account("jenner", "111");

		assertEquals("jenner", sut.getUsername());
	}

	@Test
	public void render_app() throws Exception
	{
		mockMvc.perform(get("/login"))
				.andDo(print())  // shows output on server console for debugging
				.andExpect(content().string(containsString("<h1>Login</h1>")))
				.andExpect(status().isOk());
	}
}
