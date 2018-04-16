package br.com.clinicaformare.model.acesso;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	Usuario usuario;
	TipoEntidade entidade;
	boolean inicializar;
	boolean listar;
	boolean alterar;
	boolean incluir;
	boolean deletar;
	
	// Getters and Setters
	public TipoEntidade getEntidade() {
		return entidade;
	}
	public void setEntidade(TipoEntidade entidade) {
		this.entidade = entidade;
	}
	public boolean isInicializar() {
		return inicializar;
	}
	public void setInicializar(boolean inicializar) {
		this.inicializar = inicializar;
	}
	public boolean isListar() {
		return listar;
	}
	public void setListar(boolean listar) {
		this.listar = listar;
	}
	public boolean isIncluir() {
		return incluir;
	}
	public void setIncluir(boolean incluir) {
		this.incluir = incluir;
	}
	public boolean isDeletar() {
		return deletar;
	}
	public void setDeletar(boolean deletar) {
		this.deletar = deletar;
	}
	public Usuario getUsuario() {
		return usuario;
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
