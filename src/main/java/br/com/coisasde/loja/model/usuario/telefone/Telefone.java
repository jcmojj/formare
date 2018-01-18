package br.com.coisasde.loja.model.usuario.telefone;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import br.com.coisasde.loja.model.usuario.Usuario;

@Entity
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer ddd;
	private String numero;
	@ManyToMany(mappedBy = "telefones")
	private List<Usuario> usuarios;
	@OneToOne
	private TipoTelefone tipoTelefone;

	// Constructor
	public Telefone() {
		super();
	}

	public Telefone(Integer ddd, String numero, TipoTelefone tipoTelefone) {
		super();
		this.setDdd(ddd);
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
	}

	// Getters and Setters
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return tipoTelefone;
	}

	public void setTipo(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}
