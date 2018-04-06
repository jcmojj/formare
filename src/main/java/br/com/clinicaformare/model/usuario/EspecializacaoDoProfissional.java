package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.atendimento.Atendimento;
import br.com.clinicaformare.model.atendimento.AtendimentoPorProfissional;

@Entity
public class EspecializacaoDoProfissional implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
