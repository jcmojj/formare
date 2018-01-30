package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.clinicaformare.model.usuario.Usuario;

@Entity
@Table (uniqueConstraints= {@UniqueConstraint(columnNames = {"cidade","estado","pais"})})
public class Paesci implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	private String estado;
	private String pais;
	@OneToMany(mappedBy="paesci")
	private List<Endereco> endereco;
	@OneToMany(mappedBy="localNascimento")
	private List<Usuario> usuariosLocalNascimento;
	
	// Constructor
	public Paesci() {
		super();
	}

	public Paesci(String pais, String estado, String cidade) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	// getters and setters
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Usuario> getUsuariosLocalNascimento() {
		return usuariosLocalNascimento;
	}

	public void setUsuariosLocalNascimento(List<Usuario> usuariosLocalNascimento) {
		this.usuariosLocalNascimento = usuariosLocalNascimento;
	}

}
