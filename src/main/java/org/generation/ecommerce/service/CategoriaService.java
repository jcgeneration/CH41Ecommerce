package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;
 
import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaService {
	public final CategoriaRepository categoriaRepository;
	
	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository= categoriaRepository;
	}//constructor
	
	public List<Categoria> getAllCategorias(){
		return categoriaRepository.findAll();
	}//getAllCateogorias
	
	public Categoria getCategoria(Long id) {
		return categoriaRepository.findById(id).orElseThrow(
		()-> new IllegalArgumentException("La categoría con el id ["+
				id + "] no existe")
				);
	}//getCategoria
	
	public Categoria deleteCategoria(Long id) {
		Categoria tmp = null;
		if (categoriaRepository.existsById(id)) {
			tmp=categoriaRepository.findById(id).get();
			categoriaRepository.deleteById(id);
		}//if
		return tmp;		
	}//deleteCategoria	
	
	public Categoria addCategoria(Categoria categoria) {
		Optional<Categoria> tmp= 
				categoriaRepository.findByNombre(categoria.getNombre());
		if(tmp.isEmpty()) {
			return categoriaRepository.save(categoria);
		} else {
			System.out.println("La categoría con el nombre [" + 
						categoria.getNombre() + "] ya existe");
			return null;
		}//else
	}//addCategoria
	
	public Categoria updateCategoria(Long id, String nombre, String descripcion) {
		Categoria tmp=null;
			if(categoriaRepository.existsById(id)) {
				Categoria cat=categoriaRepository.findById(id).get();
				if (nombre!=null) cat.setNombre(nombre);
				if (descripcion!=null) cat.setDescripcion(descripcion);
				categoriaRepository.save(cat);
				tmp= cat;
			}//if
		return tmp;
	}//updateCategoria
}//class CategoriaService
