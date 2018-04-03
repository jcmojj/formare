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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.model.atendimento.AtendimentoPorProfissional;
import br.com.clinicaformare.util.UsuarioLogado;

@Entity
public class Profissional implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Inject @UsuarioLogado
	private Usuario usuarioLogado;

	// Parâmetros Próprios
	@OneToOne(mappedBy = "profissional")
	Usuario usuario;
	@ManyToMany // Join para criar uma tabela única em relacionamento many to many
	@JoinTable(name = "Profissional_EspecializacaoProfissional", joinColumns = @JoinColumn(name = "Profissional_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "EspecializacaoProfissional_id", referencedColumnName = "id"))
	private List<EspecializacaoDoProfissional> especializacoesProfissional = new ArrayList<>();
	
	// Parâmetros Derivados
	@OneToMany(mappedBy = "profissional")
	private List<AtendimentoPorProfissional> atendimentoPorProfissional = new ArrayList<>();;

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
	public Profissional() {
	}

	public Profissional(Long id) {
		this.id = id;
	}

	public Profissional(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<EspecializacaoDoProfissional> getEspecializacoesProfissional() {
		return especializacoesProfissional;
	}

	public List<AtendimentoPorProfissional> getAtendimentoPorProfissional() {
		return atendimentoPorProfissional;
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
		return "Profissional [id=" + id + ", usuario=" + usuario + "]";
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
		Profissional other = (Profissional) obj;
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
