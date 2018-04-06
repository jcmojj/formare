package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Entity
public class NivelProfissional implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Parâmetros Próprios
	private String nivel;
	
	// Parâmetros Derivados
	@OneToMany (mappedBy = "nivelProfissional")
	private List<EspecializacaoDoProfissional> especializacoesDoProfissional;
	
	// Constructor
	public NivelProfissional() {
		super();
	}
	public NivelProfissional(Long id) {
		super();
		this.id = id;
	}	
	public NivelProfissional(String nivel) {
		super();
		this.nivel = nivel;
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}
	public String getNivel() {
		return nivel;
	}
	public List<EspecializacaoDoProfissional> getEspecializacoesDoProfissional() {
		return especializacoesDoProfissional;
	}
	
	// String, hashCode and Equals
	@Override
	public String toString() {
		return "NivelProfissional [id=" + id + ", nivel=" + nivel + "]";
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
		NivelProfissional other = (NivelProfissional) obj;
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

}
