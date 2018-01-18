package br.com.coisasde.loja.model.usuario.endereco;

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
	private Integer id;
	@Column(unique = true)
	private String logradouro;
	@OneToMany(mappedBy = "logradouro")
	private List<Endereco> endereco;

	// Construtor
	public Logradouro() {
		super();
	}

	public Logradouro(String logradouro) {
		super();
		this.logradouro = logradouro;
	}

	// Getters and setters
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

}