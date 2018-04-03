package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;
import br.com.clinicaformare.model.usuario.Socia;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.UsuarioLogado;

@Entity
public class Pacote implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	@Inject
	private UsuarioLogado usuarioLogado;

//	@Transient
//	private String nome;
	
	// Parâmetros Próprios
//	private BigDecimal descontoGeral;
//	private BigDecimal valorPagoPeloResponsavel;
	private BigDecimal descontoValorRecebidoDoResponsavel;
	private BigDecimal descontoValorPagoProfissional;
	@ManyToOne
	private Socia sociaResponsavel;
	@ManyToOne
	private Socia sociaSupervisora;
	@ManyToOne
	private ResponsavelFinanceiro responsavelFinanceiro;
	@ManyToOne
	private Paciente paciente; // deve ser um dependente do contratante !!!relacao
	private Integer quantidadeAtendimentosContratados;
	private LocalDate dataInicioAtendimento;
	private LocalDate dataReferenciaPacote;
	//CLYMA
	//info de nota fiscal
	
	// private List<Cliente> pacientesPermitidos;

	@OneToMany(mappedBy = "pacote")
	private List<Atendimento> atendimentos = new ArrayList<>();


	// Parâmetros de Persistência
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	@OneToOne
	private Usuario alteradoPor;
	@OneToOne
	private Usuario criadoPor;
		
	// Constructor
	public Pacote() {
		super();
	}

	public Pacote(Long id) {
		super();
		this.id = id;
	}
	public Pacote(ResponsavelFinanceiro responsavelFinanceiro, Paciente paciente, List<Atendimento> atendimentos, BigDecimal descontoValorRecebidoDoResponsavel, BigDecimal descontoValorPagoProfissional, Integer quantidadeAtendimentos) {
		super();
		this.responsavelFinanceiro = responsavelFinanceiro;
		responsavelFinanceiro.getPacotes().add(this);
		this.paciente = paciente;
		paciente.getPacotes().add(this);
		this.sociaResponsavel = usuarioLogado.getUsuarioLogado().getSocia();
		this.sociaSupervisora = usuarioLogado.getUsuarioLogado().getSocia();
		sociaResponsavel.getPacotes().add(this);
		this.atendimentos.addAll(atendimentos);
		atendimentos.forEach(a -> a.setPacote(this));
		this.descontoValorRecebidoDoResponsavel = descontoValorRecebidoDoResponsavel;
		this.descontoValorPagoProfissional = descontoValorPagoProfissional;
		this.dataInicioAtendimento = atendimentos.stream().map(a -> a.getData()).min(Comparator.comparing(LocalDate::toEpochDay)).orElse(LocalDate.now()); // pegar a data do primeiro atendimento do pacote
		this.dataReferenciaPacote = this.dataCriacao.toLocalDate();
		this.quantidadeAtendimentosContratados = quantidadeAtendimentos;

	}
//	public Pacote(Socia sociaResponsavel, ResponsavelFinanceiro responsavelFinanceiro) {
//		super();
//		
//		
//		
//		this.sociaResponsavel = sociaResponsavel;
//		this.responsavelFinanceiro = responsavelFinanceiro;
//		sociaResponsavel.getPacotes().add(this);
//		responsavelFinanceiro.getPacotes().add(this);
//	}
	
	
	// Getters and setters
	public Socia getSociaResponsavel() {
		return sociaResponsavel;
	}

	public void setSociaResponsavel(Socia sociaResponsavel) {
		this.sociaResponsavel = sociaResponsavel;
	}

	public ResponsavelFinanceiro getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}

	public void setResponsavelFinanceiro(ResponsavelFinanceiro responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}

	public Long getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public Usuario getAlteradoPor() {
		return alteradoPor;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public LocalDate getDataReferenciaPacote() {
		return dataReferenciaPacote;
	}

	public void setDataReferenciaPacote(LocalDate dataReferenciaPacote) {
		this.dataReferenciaPacote = dataReferenciaPacote;
	}

	public Integer getQuantidadeAtendimentos() {
		return quantidadeAtendimentosContratados;
	}

	public BigDecimal getValorPagoPeloResponsavel() {
		return new BigDecimal("0");
	}

	public LocalDate getDataInicioAtendimento() {
		return dataInicioAtendimento;
	}

	public BigDecimal getDescontoValorRecebidoDoResponsavel() {
		return descontoValorRecebidoDoResponsavel;
	}

	public void setDescontoValorRecebidoDoResponsavel(BigDecimal descontoValorRecebidoDoResponsavel) {
		this.descontoValorRecebidoDoResponsavel = descontoValorRecebidoDoResponsavel;
	}

	public BigDecimal getDescontoValorPagoProfissional() {
		return descontoValorPagoProfissional;
	}

	public void setDescontoValorPagoProfissional(BigDecimal descontoValorPagoProfissional) {
		this.descontoValorPagoProfissional = descontoValorPagoProfissional;
	}

	// String, hashCode and Equals
	@Override
	public String toString() {
		return "Pacote [id=" + id + ", sociaResponsavel=" + sociaResponsavel + ", contratante=" + responsavelFinanceiro + ", paciente=" + paciente + ", atendimentos=" + atendimentos
				 + "]";
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
		Pacote other = (Pacote) obj;
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
		this.dataCriacao = LocalDateTime.now();
		this.dataAlteracao = LocalDateTime.now();
		this.criadoPor = usuarioLogado.getUsuarioLogado();
		this.alteradoPor = usuarioLogado.getUsuarioLogado();
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = LocalDateTime.now();;
		this.alteradoPor = usuarioLogado.getUsuarioLogado();
	}

}

//// Getters and setters - Modificados
//public String getNome(String nome) {
//	return "a";
//}
