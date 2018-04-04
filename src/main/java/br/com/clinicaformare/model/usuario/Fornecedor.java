package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.util.listeners.login.UsuarioLogado;

@Entity
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Inject @UsuarioLogado
	private Usuario usuarioLogado;
	
	// Parâmetros Próprios
	private String RazaoSocial;
	private String apelido;
//	@CPF
//	private CPF cpf;
//	@CNPJ
//	private CNPJ cnpj;
	@OneToOne(mappedBy = "fornecedor")
	Usuario usuario;
	@JoinTable(name = "Fornecedor_TipoFornecedor", joinColumns = @JoinColumn(name = "Fornecedor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "TipoFornecedor_id", referencedColumnName = "id"))
	@ManyToMany
	private List<TipoFornecedor> tiposFornecedor = new ArrayList<>();

	// Parâmetros de Persistência
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;
	@OneToOne
	private Usuario alteradoPor;
	@OneToOne
	private Usuario criadoPor;

	// Constructor
	public Fornecedor() {
	}

	public Fornecedor(Long id) {
		this.id = id;
	}
	public Fornecedor(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<TipoFornecedor> getTiposFornecedors() {
		return tiposFornecedor;
	}
	public String getRazaoSocial() {
		return RazaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

//	public CPF getCpf() {
//		return cpf;
//	}
//
//	public void setCpf(CPF cpf) {
//		this.cpf = cpf;
//	}
//
//	public CNPJ getCnpj() {
//		return cnpj;
//	}
//
//	public void setCnpj(CNPJ cnpj) {
//		this.cnpj = cnpj;
//	}

	public List<TipoFornecedor> getTiposFornecedor() {
		return tiposFornecedor;
	}


	// String, hashCode and Equals
	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", usuario[id]=" + usuario.getId() + "]";
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
		Fornecedor other = (Fornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// Método Callback para persistir
	@PrePersist
	public void quandoCriar() {
		this.dataCriacao = (Calendar.getInstance());
		this.dataAlteracao = (Calendar.getInstance());
		this.criadoPor = usuarioLogado;
		this.alteradoPor = usuarioLogado;
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = (Calendar.getInstance());
		this.alteradoPor = usuarioLogado;
	}
}