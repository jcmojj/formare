package br.com.coisasde.loja.model.usuario.telefone;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TipoTelefone implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true)
	private String tipo;
	@OneToOne(mappedBy = "tipoTelefone")
	private Telefone telefone;
	
	// Constructor
	public TipoTelefone(String tipo) {
		super();
		this.tipo = tipo;
	}

	public TipoTelefone() {
		super();
	}
	
	//Getters and Setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "TipoTelefone [id=" + id + ", tipo=" + tipo + "]";
	}

}
