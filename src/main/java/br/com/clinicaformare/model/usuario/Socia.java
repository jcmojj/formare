package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;
import br.com.clinicaformare.model.atendimento.Pacote;

@Entity
public class Socia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "socia")
	Usuario usuario;
	@ManyToOne
	TipoProfissional tipoProfissional;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;


	@OneToMany(mappedBy = "sociaResponsavel")
	private List<Pacote> pacotes;
	@OneToMany(mappedBy = "socia")
	private List<AtendimentoPadrao> atendimentosPadrao;
	@JoinTable(name = "Socia_TipoProfissional", joinColumns = @JoinColumn(name = "Socia_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "TipoProfissional_id", referencedColumnName = "id"))
	@ManyToMany
	private List<TipoProfissional> tiposProfissionais;

	@Override
	public String toString() {
		return "Socia [id=" + id + ", usuario=" + usuario + ", tipoProfissional=" + tipoProfissional + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + ", pacotes=" + pacotes
				+ "]";
	}

	// Constructor
	public Socia() {
	}

	public Socia(Long id) {
		this.id = id;
	}
	public Socia(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	// Getters and setters


	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public TipoProfissional getTipoProfissional() {
		return tipoProfissional;
	}

	public void setTipoProfissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	public List<AtendimentoPadrao> getAtendimentosPadrao() {
		return atendimentosPadrao;
	}

	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
		this.atendimentosPadrao = atendimentosPadrao;
	}

	public List<TipoProfissional> getTiposProfissionais() {
		return tiposProfissionais;
	}

	public void setTiposProfissionais(List<TipoProfissional> tiposProfissionais) {
		this.tiposProfissionais = tiposProfissionais;
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
		Socia other = (Socia) obj;
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
		this.setDataCriacao(Calendar.getInstance());
		this.setDataAlteracao(Calendar.getInstance());
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.setDataAlteracao(Calendar.getInstance());
	}
}