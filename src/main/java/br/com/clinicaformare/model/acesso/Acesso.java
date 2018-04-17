package br.com.clinicaformare.model.acesso;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.usuario.Usuario;

@Entity
public class Acesso implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	// Variáveis
	@ManyToOne
	Usuario usuario; // null quando for os de tipoUsurio
	@Enumerated(EnumType.STRING)
	TipoUsuario tipoUsuario;  // null quando for de usuario
	@Enumerated(EnumType.STRING)
	TipoEntidade tipoEntidade;
	private Boolean inicializar;
	private Boolean listar;
	private Boolean alterar;
	private Boolean incluir;
	private Boolean deletar;
	
	// Constructor
	public Acesso() {
		super();
	}
	public Acesso(Usuario usuario) {
		super();
		this.tipoUsuario = null;
		this.usuario = usuario;
	}
	public Acesso(TipoUsuario tipoUsuario) {
		super();
		this.usuario = null;
		this.tipoUsuario = tipoUsuario;
	}
	// Getters and Setters
	public TipoEntidade getTipoEntidade() {
		return tipoEntidade;
	}
	public void setTipoEntidade(TipoEntidade entidade) {
		this.tipoEntidade = entidade;
	}
	public Boolean isInicializar() {
		return inicializar;
	}
	public void setInicializar(Boolean inicializar) {
		this.inicializar = inicializar;
	}
	public Boolean isListar() {
		return listar;
	}
	public void setListar(Boolean listar) {
		this.listar = listar;
	}
	public Boolean isAlterar() {
		return alterar;
	}
	public void setAlterar(Boolean alterar) {
		this.alterar = alterar;
	}
	public Boolean isIncluir() {
		return incluir;
	}
	public void setIncluir(Boolean incluir) {
		this.incluir = incluir;
	}
	public Boolean isDeletar() {
		return deletar;
	}
	public void setDeletar(Boolean deletar) {
		this.deletar = deletar;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	// Tostring, Equal e Hashcode
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
		Acesso other = (Acesso) obj;
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
		this.criador = 		(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		this.alterador = 	(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = (LocalDateTime.now());
		this.alterador  = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	
}
