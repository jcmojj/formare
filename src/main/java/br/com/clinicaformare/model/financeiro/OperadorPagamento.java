package br.com.clinicaformare.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.clinicaformare.model.financeiro.operador.TarifaOperacaoFinanceira;

public class OperadorPagamento implements Serializable{
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal tarifaAntecipacaoRecebiveis;
	private BigDecimal tarifaPorTransferencia;
	private BigDecimal taxaPorConta;
	private BigDecimal taxaDeProcessamento;
	private BigDecimal taxaFixaPorPagamentoBoleto;
	private BigDecimal taxaVariavelPorPagamentoBoleto;
	private BigDecimal taxaFixaPorPagamentoCartao;
	private BigDecimal taxaVariavelPorPagamentoCartao;
	
	
	@Override
	public String toString() {
		return "OperadorPagamento [id=" + id + ", nome=" + nome + ", tarifaAntecipacaoRecebiveis=" + tarifaAntecipacaoRecebiveis + ", tarifaPorTransferencia=" + tarifaPorTransferencia
				+ ", taxaPorConta=" + taxaPorConta + ", taxaDeProcessamento=" + taxaDeProcessamento + ", taxaFixaPorPagamentoBoleto=" + taxaFixaPorPagamentoBoleto + ", taxaVariavelPorPagamentoBoleto="
				+ taxaVariavelPorPagamentoBoleto + ", taxaFixaPorPagamentoCartao=" + taxaFixaPorPagamentoCartao + ", taxaVariavelPorPagamentoCartao=" + taxaVariavelPorPagamentoCartao + "]";
	}
	
	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getTarifaAntecipacaoRecebiveis() {
		return tarifaAntecipacaoRecebiveis;
	}
	public void setTarifaAntecipacaoRecebiveis(BigDecimal tarifaAntecipacaoRecebiveis) {
		this.tarifaAntecipacaoRecebiveis = tarifaAntecipacaoRecebiveis;
	}
	public BigDecimal getTarifaPorTransferencia() {
		return tarifaPorTransferencia;
	}
	public void setTarifaPorTransferencia(BigDecimal tarifaPorTransferencia) {
		this.tarifaPorTransferencia = tarifaPorTransferencia;
	}
	public BigDecimal getTaxaPorConta() {
		return taxaPorConta;
	}
	public void setTaxaPorConta(BigDecimal taxaPorConta) {
		this.taxaPorConta = taxaPorConta;
	}
	public BigDecimal getTaxaDeProcessamento() {
		return taxaDeProcessamento;
	}
	public void setTaxaDeProcessamento(BigDecimal taxaDeProcessamento) {
		this.taxaDeProcessamento = taxaDeProcessamento;
	}
	public BigDecimal getTaxaFixaPorPagamentoBoleto() {
		return taxaFixaPorPagamentoBoleto;
	}
	public void setTaxaFixaPorPagamentoBoleto(BigDecimal taxaFixaPorPagamentoBoleto) {
		this.taxaFixaPorPagamentoBoleto = taxaFixaPorPagamentoBoleto;
	}
	public BigDecimal getTaxaVariavelPorPagamentoBoleto() {
		return taxaVariavelPorPagamentoBoleto;
	}
	public void setTaxaVariavelPorPagamentoBoleto(BigDecimal taxaVariavelPorPagamentoBoleto) {
		this.taxaVariavelPorPagamentoBoleto = taxaVariavelPorPagamentoBoleto;
	}
	public BigDecimal getTaxaFixaPorPagamentoCartao() {
		return taxaFixaPorPagamentoCartao;
	}
	public void setTaxaFixaPorPagamentoCartao(BigDecimal taxaFixaPorPagamentoCartao) {
		this.taxaFixaPorPagamentoCartao = taxaFixaPorPagamentoCartao;
	}
	public BigDecimal getTaxaVariavelPorPagamentoCartao() {
		return taxaVariavelPorPagamentoCartao;
	}
	public void setTaxaVariavelPorPagamentoCartao(BigDecimal taxaVariavelPorPagamentoCartao) {
		this.taxaVariavelPorPagamentoCartao = taxaVariavelPorPagamentoCartao;
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
		OperadorPagamento other = (OperadorPagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
