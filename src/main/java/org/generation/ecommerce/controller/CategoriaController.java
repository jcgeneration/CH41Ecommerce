package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/categorias/")
public class CategoriaController {
	private final CategoriaService categoriaService; 	
	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService= categoriaService;
	}//constructor
		@GetMapping
		public List<Categoria> getCategorias() {
			return categoriaService.getAllCategorias();
		}//getAll
		@GetMapping(path="{catId}") // http://localhost:8080/api/categoria/1
		public Categoria getCategoria(@PathVariable("catId") Long id) {
			return categoriaService.getCategoria(id);
		}//get
		@DeleteMapping(path="{catId}") // http://localhost:8080/api/categoria/1
		public Categoria deleteCategoria(@PathVariable("catId") Long id) {
			return categoriaService.deleteCategoria(id);
		}//delete
		
		@PostMapping     // http://localhost:8080/api/categoria/
		public Categoria addCategoria(@RequestBody Categoria cat) {
			return categoriaService.addCategoria(cat);
		}//add
		
		@PutMapping(path="{prodId}") // http://localhost:8080/api/categoria/1
		public Categoria updateCategoria(@PathVariable("prodId") Long id,
				@RequestParam(required=false) String nombre, 
				@RequestParam(required=false) String descripcion) {
			return categoriaService.updateCategoria(id, nombre, descripcion);
		}//update		
}//CategoriaController

