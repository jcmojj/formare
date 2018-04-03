package br.com.clinicaformare.util.listeners;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.persistence.PostPersist;

import br.com.clinicaformare.daos.financeiro.PagamentoDao;
import br.com.clinicaformare.model.financeiro.Pagamento;

public class PagamentoListener {
	
	
	@Inject
	PagamentoDao pagamentoDao;
	
	private BigDecimal getTaxaCacaio(BigDecimal totalFaturamento) {
		Long cotaInferior = 100L * 1000;
		Long cotaIntermediaria = 200L *1000;
		Long cotaSuperior = 300L * 1000;
		BigDecimal taxaInferior = new BigDecimal("2.5").divide(new BigDecimal("100"));
		BigDecimal taxaIntermediaria = new BigDecimal("3.5").divide(new BigDecimal("100"));
		BigDecimal taxaSuperior = new BigDecimal("5").divide(new BigDecimal("100"));
		BigDecimal retorno = new BigDecimal("0");
		
		
		if(totalFaturamento.compareTo(new BigDecimal(cotaInferior)) < 0) {
			retorno = taxaInferior;
		}
		if(totalFaturamento.compareTo(new BigDecimal(cotaIntermediaria)) > 0) {
			retorno = taxaIntermediaria;
		}
		if(totalFaturamento.compareTo(new BigDecimal(cotaSuperior)) > 0) {
			retorno = taxaSuperior;
		}
		return retorno;
	}
	
	// criar esse metodo
	@PostPersist
	public void newPagamento(Pagamento newPagamento) {
		Integer diaDoMes = 0;
		BigDecimal faturamentoTotal = pagamentoDao.faturamentoTotalDesdeDia(diaDoMes);
		// adicionar no lugar do 2.5 o valor correto e fazer o mesmo pra taxa de operacoes com o contador de operacoes vide calculadora de desconto
	}
	
//	@PrePersist
	
	
	
}
