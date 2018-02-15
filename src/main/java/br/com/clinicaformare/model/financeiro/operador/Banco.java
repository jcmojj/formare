package br.com.clinicaformare.model.financeiro.operador;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banco implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = false, nullable = false)
	private String nome;
	@Column(unique = false, nullable = false)
	private String codigoCompensacao;
	@Column(nullable = false)
	private String email;

	// Constructor
	public Banco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Banco(String nome, String codigoCompensacao) {
		super();
		this.nome = nome;
		this.codigoCompensacao = codigoCompensacao;
	}
	
	public Banco(String nome, String codigoCompensacao, String email) {
		super();
		this.nome = nome;
		this.codigoCompensacao = codigoCompensacao;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Banco [id=" + id + ", nome=" + nome + ", codigoCompensacao=" + codigoCompensacao + "]";
	}

	// Getters and Setters
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

	public String getCodigoCompensacao() {
		return codigoCompensacao;
	}

	public void setCodigoCompensacao(String codigoCompensacao) {
		this.codigoCompensacao = codigoCompensacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banco other = (Banco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}