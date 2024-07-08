package org.generation.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 0. Cambiar en el application properties por update
// 1. Constructor (email, password)
// 2. Constructor vacío
// 3. getter and setters (id->readonly)
// 4. toString

// UsuarioController - NO hacer el update (PUT)
// UsuarioService    - NO hacer el update (PUT)
// UsuarioRepository
@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}//constructor
	
	public Usuario() {} //constructor vacío

	public String getEmail() {
		return email;
	}//getEmail

	public void setEmail(String email) {
		this.email = email;
	}//setEmail

	public String getPassword() {
		return password;
	}//getPassword

	public void setPassword(String password) {
		this.password = password;
	}//setPassword

	public Long getId() {
		return id;
	}//getId

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + "]";
	}//toString
	
}//class Usuario
