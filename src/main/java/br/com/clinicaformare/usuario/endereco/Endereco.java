package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.clinicaformare.model.usuario.Usuario;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private TipoEndereco tipoEndereco;
	@ManyToOne
	private Logradouro logradouro;
	private String endereco;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	@ManyToOne
	private Paesci paesci;
	@ManyToMany(mappedBy = "enderecos")
	private List<Usuario> usuarios;

	// Constructor
	public Endereco() {
		super();
	}

	public Endereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
		this.logradouro = new Logradouro();
	}

	// Getters and Setters
	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Paesci getPaesci() {
		return paesci;
	}

	public void setPaesci(Paesci paesci) {
		this.paesci = paesci;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}