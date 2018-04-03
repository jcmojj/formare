package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.model.usuario.EspecializacaoDoProfissional;
import br.com.clinicaformare.model.usuario.Profissional;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.UsuarioLogado;

@Entity
public class AtendimentoPorProfissional implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Inject @UsuarioLogado
	private Usuario usuarioLogado;
	
	
	// Parâmetros Próprios
	private BigDecimal valorPagoProfissional; // já é um valor com desconto
	private BigDecimal descontoRepassadoAoProfissional;
	private Integer quantidadeProfissionaisPagos;
	private boolean realizado;
	@ManyToOne
	private Profissional profissional;
	@ManyToOne
	private EspecializacaoDoProfissional especializacaoDoProfissional;
	@ManyToOne
	private Atendimento atendimento;
	
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
	public AtendimentoPorProfissional() {
		super();
	}
	public AtendimentoPorProfissional(Long id) {
		super();
		this.id = id;
	}
	public AtendimentoPorProfissional(Atendimento atendimento, Profissional profissional, EspecializacaoDoProfissional especializacaoDoProfissional) {
		this.atendimento = atendimento;
		this.profissional = profissional;
		this.especializacaoDoProfissional = especializacaoDoProfissional;
		this.descontoRepassadoAoProfissional = atendimento.getDescontoPacotePaciente();
		this.quantidadeProfissionaisPagos = atendimento.getQuantidadeProfissionaisPagos();// rever
		if(quantidadeProfissionaisPagos == 0) {
			this.valorPagoProfissional = new BigDecimal("0");
		}else {
			this.valorPagoProfissional = especializacaoDoProfissional.getValorPagoProfissional().multiply((new BigDecimal("1").subtract(descontoRepassadoAoProfissional))).divide(new BigDecimal(quantidadeProfissionaisPagos));
		}
		this.realizado = false;
	}
	// Getters and Setters
	public BigDecimal getValorPagoProfissional() {
		return valorPagoProfissional;
	}
	public BigDecimal getDescontoRepassadoAoProfissional() {
		return descontoRepassadoAoProfissional;
	}
	public Profissional getProfissional() {
		return profissional;
	}
	public EspecializacaoDoProfissional getEspecializacaoDoProfissional() {
		return especializacaoDoProfissional;
	}
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public boolean isRealizado() {
		return realizado;
	}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}
	
	// String, hashCode and Equals
	@Override
	public String toString() {
		return "AtendimentoPorProfissional [id=" + id + ", profissional=" + profissional + ", especializacaoDoProfissional=" + especializacaoDoProfissional + ", atendimento=" + atendimento + "]";
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
		AtendimentoPorProfissional other = (AtendimentoPorProfissional) obj;
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
