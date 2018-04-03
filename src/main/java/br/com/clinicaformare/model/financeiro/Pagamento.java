package br.com.clinicaformare.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.model.financeiro.operador.TarifaOperacaoFinanceira;
import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;
import br.com.clinicaformare.util.listeners.PagamentoListener;

@Entity
@EntityListeners(PagamentoListener.class)
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal valor;
	
	private boolean foiPago;
	
	@ManyToOne
	private TarifaOperacaoFinanceira tarifa;
	@ManyToOne
	private ResponsavelFinanceiro responsavelFinanceiro;
	@ManyToOne
	private Paciente paciente;

//	@Transient
//	List<AtendimentoPadrao> atendimentosPadrao = new ArrayList<>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;// = Calendar.getInstance();
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	// public Pagamento(List<AtendimentoPadrao> atendimentosPadrao, TipoPagamento tipo, OperadorPagamento operador) {
	// super();
	//// AtendimentoPadrao atendimentoPadrao;
	// for(AtendimentoPadrao atendimento: atendimentosPadrao) {
	// Double valorBrutoHoraSemDesconto = atendimento.getValorBrutoHoraSemDesconto();
	// Double valorLiquidoHoraSemDesconto = atendimento.getValorLiquidoHoraSemDesconto();
	//// Double valorBrutoHora = atendimento.getValorBrutoHora();
	//// Double valorLiquidoHora = atendimento.getValorLiquidoHora();
	//
	//
	//
	//
	//
	// this.atendimentosPadrao.add(atendimento);
	//
	// }
	//
	// this.valor = valor;
	// this.tipo = tipo;
	// this.operador = operador;
	// }

	// Outros Métodos
	public boolean ehPrimeiraPagamentoMensalDe() {
		return true;
	}
	public boolean foramRealizadosQuantosPagamentosNoMes() {
		return true;
	}
	public boolean qualOperadorAtual() {
		return true;
	}
	
	public BigDecimal getTaxaFixaTotalOperacao(){
		
		
		return BigDecimal.ZERO;
	}
	public BigDecimal getTaxaVariavelTotalOperacao(){
		
		
		return BigDecimal.ZERO;
	}

	// Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

//	public TipoPagamento getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoPagamento tipo) {
//		this.tipo = tipo;
//	}
//
//	public OperadorPagamento getOperador() {
//		return operador;
//	}
//
//	public void setOperador(OperadorPagamento operador) {
//		this.operador = operador;
//	}

	public boolean isFoiPago() {
		return foiPago;
	}

	public void setFoiPago(boolean foiPago) {
		this.foiPago = foiPago;
	}

	public ResponsavelFinanceiro getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}

	public void setResponsavelFinanceiro(ResponsavelFinanceiro responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

//	public List<AtendimentoPadrao> getAtendimentosPadrao() {
//		return atendimentosPadrao;
//	}
//
//	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
//		this.atendimentosPadrao = atendimentosPadrao;
//	}

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

	
	// Método Callback para persist
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
	
	// Equals e HashCode
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
