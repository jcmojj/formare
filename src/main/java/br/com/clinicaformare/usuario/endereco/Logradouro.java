package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Logradouro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nome;
	@OneToMany(mappedBy = "logradouro")
	private List<Endereco> endereco;

	// Construtor
	public Logradouro() {
		super();
	}

	public Logradouro(String nome) {
		super();
		this.nome = nome;
	}

	// Getters and setters
	public String getLogradouro() {
		return nome;
	}

	public void setLogradouro(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}