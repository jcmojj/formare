package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.clinicaformare.model.atendimento.Atendimento;
import br.com.clinicaformare.model.atendimento.AtendimentoPorProfissional;
import br.com.clinicaformare.util.UsuarioLogado;

@Entity
public class EspecializacaoDoProfissional implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	@Inject
	private UsuarioLogado usuarioLogado;

	// Parâmetros Próprios
	private String especializacao;
	private BigDecimal valorRecebidoDoResponsavel;
	private BigDecimal valorPagoProfissional;
	private boolean especializacaoDeSocia;
	@ManyToOne
	private NivelProfissional nivelProfissional;
	
	// Parâmetros Derivados
	@OneToMany(mappedBy = "especializacaoProfissional")
	private List<Atendimento> atendimentos = new ArrayList<>();
	@OneToMany(mappedBy = "especializacaoDoProfissional")
	private List<AtendimentoPorProfissional> atendimentosPorProfissional = new ArrayList<>();
	@ManyToMany(mappedBy = "especializacoesProfissional")
	private List<Profissional> profissionais = new ArrayList<>();
	
	
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
	public EspecializacaoDoProfissional() {
		super();
	}
	public EspecializacaoDoProfissional(String especializacao, BigDecimal valorRecebidoDoResponsavel, BigDecimal valorPagoProfissional, boolean especializacaoDeSocia, NivelProfissional nivelProfissional) {
		super();
		this.especializacao = especializacao;
		this.valorRecebidoDoResponsavel = valorRecebidoDoResponsavel;
		this.valorPagoProfissional = valorPagoProfissional;
		this.especializacaoDeSocia = especializacaoDeSocia;
		this.nivelProfissional = nivelProfissional;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}
	public String getEspecializacao() {
		return especializacao;
	}
	public BigDecimal getValorRecebidoDoResponsavel() {
		return valorRecebidoDoResponsavel;
	}
	public BigDecimal getValorPagoProfissional() {
		return valorPagoProfissional;
	}
	public boolean isEspecializacaoDeSocia() {
		return especializacaoDeSocia;
	}
	public NivelProfissional getNivelProfissional() {
		return nivelProfissional;
	}
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public List<AtendimentoPorProfissional> getAtendimentosPorProfissional() {
		return atendimentosPorProfissional;
	}
	public List<Profissional> getProfissionais() {
		return profissionais;
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
		return "EspecializacaoProfissional [id=" + id + ", especializacao=" + especializacao + "]";
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
		EspecializacaoDoProfissional other = (EspecializacaoDoProfissional) obj;
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
		this.criadoPor = usuarioLogado.getUsuarioLogado();
		this.alteradoPor = usuarioLogado.getUsuarioLogado();
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = (Calendar.getInstance());
		this.alteradoPor = usuarioLogado.getUsuarioLogado();
	}

}
