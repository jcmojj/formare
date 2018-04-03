package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.util.UsuarioLogado;

@Entity
public class NivelProfissional implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Inject @UsuarioLogado
	private Usuario usuarioLogado;
	
	// Parâmetros Próprios
	private String nivel;
	
	// Parâmetros Derivados
	@OneToMany (mappedBy = "nivelProfissional")
	private List<EspecializacaoDoProfissional> especializacoesDoProfissional;
	
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
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}
	public Usuario getAlteradoPor() {
		return alteradoPor;
	}
	public Usuario getCriadoPor() {
		return criadoPor;
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
