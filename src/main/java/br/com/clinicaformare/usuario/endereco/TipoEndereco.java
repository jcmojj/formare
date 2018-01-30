package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TipoEndereco implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String tipo;
	@OneToOne(mappedBy = "tipoEndereco")
	private Endereco telefone;
	
	// Constructor
	public TipoEndereco(String tipo) {
		super();
		this.tipo = tipo;
	}

	public TipoEndereco() {
		super();
	}
	
	public TipoEndereco(Long id) {
		super();
		this.id = id;
	}

	//Getters and Setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return telefone;
	}

	public void setEndereco(Endereco telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TipoEndereco [id=" + id + ", tipo=" + tipo + "]";
	}

}
