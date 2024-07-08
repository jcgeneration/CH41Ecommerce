package org.generation.ecommerce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecommerce.model.Producto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {
	@Autowired
	private MockMvc mockMvc;
		
	private final String token = "Bearer: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbXlAZ21haWwuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3MjA0NTc0NDksImV4cCI6MTcyMDQ5MzQ0OX0.4UZKsDlJ_xD_Q2dO6SpBHAjdkAdVvOOV67a-IEIiRnM";
	
	@Test
	@DisplayName("Se prueba el GET del endpoint http://localhost:8080/api/productos/1")
	void pruebaGET() throws Exception {
		this.mockMvc.perform( get("/api/productos/1")  )
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("norma1.gif"))
				);
		this.mockMvc.perform( get("/api/productos/5")  )
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("Plumas Bic Cristal Intenso 3 piezas"))
				);
	}//pruebaGET

	@Test
	@DisplayName("Se prueba el DELETE del endpoint http://localhost:8080/api/productos/7")
	@Disabled("Probado una vez, deshabilitado porque ya no existe el producto id 7")
	void pruebaDELETE() throws Exception {
		this.mockMvc.perform( delete("/api/productos/7")
				.header("Authorization", token)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("Plumas Bic Cristal Intenso 12 piezas"))
				);
	}//pruebaDELETE
	
	@Test
	@DisplayName("Se prueba crear un producto nuevo (POST)")
	@Disabled("Se creó el producto Plumas Bic Cristal Intenso 12 piezas y se generó el id 8")
	void pruebaPOST() throws Exception {
		Producto p = new Producto();
		p.setNombre("Plumas Bic Cristal Intenso 12 piezas");
		p.setDescripcion("Plumas Bic Cristal Dura Más / Punto mediano / Tinta negra / 12 piezas");
		p.setImagen("515ftw-2.jpg");
		p.setPrecio(150.95);
		
		this.mockMvc.perform( post("/api/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content( asJsonString(p) )
				.header("Authorization", token)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("Plumas Bic Cristal Intenso 12 piezas"))
				);
	}//pruebaPOST
	
	@Test
	@DisplayName("Se prueba modificar un producto id 4")
	
	void pruebaPUT() throws Exception {
		this.mockMvc.perform( put("/api/productos/4")
				.queryParam("precio", "49.95")
				.header("Authorization", token)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("49.95"))
				);
	}//pruebaPUT
	
	private static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }//catch
	 } // asJsonString
}//class EcommerceApplicationTests
