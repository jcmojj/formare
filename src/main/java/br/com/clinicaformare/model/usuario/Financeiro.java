package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Financeiro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	// Parâmetros Próprios
	private String RazaoSocial;
	private String apelido;
//	@CPF
//	private CPF cpf;
//	@CNPJ
//	private CNPJ cnpj;
	@OneToOne(mappedBy = "financeiro")
	Usuario usuario;
//	@JoinTable(name = "Financeiro_TipoFinanceiro", joinColumns = @JoinColumn(name = "Financeiro_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "TipoFinanceiro_id", referencedColumnName = "id"))
//	@ManyToMany
//	private List<TipoFinanceiro> tiposFinanceiro = new ArrayList<>();


	// Constructor
	public Financeiro() {
	}

	public Financeiro(Long id) {
		this.id = id;
	}
	public Financeiro(Usuario usuario) {
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
//	
//	public List<TipoFinanceiro> getTiposFinanceiros() {
//		return tiposFinanceiro;
//	}
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

//	public List<TipoFinanceiro> getTiposFinanceiro() {
//		return tiposFinanceiro;
//	}


	// String, hashCode and Equals
	@Override
	public String toString() {
		return "Financeiro [id=" + id + ", usuario[id]=" + usuario.getId() + "]";
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
		Financeiro other = (Financeiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// -----------------------------------Registro de Alteração-----------------------------------------
	// Parâmetros de Persistência
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	@ManyToOne
	private Usuario alterador;
	@ManyToOne
	private Usuario criador;
	
	// Getters de persistência
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}
	public Usuario getAlterador() {
		return alterador;
	}
	public Usuario getCriador() {
		return criador;
	}
	
	// Método Callback para persistir
	@PrePersist
	public void quandoCriar() {
		this.dataCriacao = (LocalDateTime.now());
		this.dataAlteracao = (LocalDateTime.now());
		this.criador = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		this.alterador = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = (LocalDateTime.now());
		this.alterador  = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	// ------------------------------------------------------------------------------------------------
	public Class<?> getClasse(){
		return this.getClass();
	}
}