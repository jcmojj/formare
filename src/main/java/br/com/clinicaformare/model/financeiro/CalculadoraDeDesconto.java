package br.com.clinicaformare.model.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import br.com.clinicaformare.daos.financeiro.PagamentoDao;
import br.com.clinicaformare.model.financeiro.operador.TarifaOperacaoFinanceira;
import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;

public class CalculadoraDeDesconto {

	private TarifaOperacaoFinanceira tarifa;
	private ResponsavelFinanceiro responsavelFinanceiro;
	private Paciente paciente;
//	List<AtendimentoPadrao> atendimentosPadrao = new ArrayList<>();
	
	@Inject
	PagamentoDao pagamentoDao;
	
	// Quantidade de Pagamentos Em Período
	public CalculadoraDeDesconto(TarifaOperacaoFinanceira tarifa, ResponsavelFinanceiro responsavelFinanceiro, Paciente paciente) {//, List<AtendimentoPadrao> atendimentosPadrao) {
		super();
		this.tarifa = tarifa;
		this.responsavelFinanceiro = responsavelFinanceiro;
		this.paciente = paciente;
//		this.atendimentosPadrao = atendimentosPadrao;
	}
	public Long quantidadePagamentosMensaisDeResponsavelFinanceiroDesde(Calendar data) {
		return pagamentoDao.pagamentosDeResponsavelFinanceiroDesdeData(responsavelFinanceiro,  data);
	}
	public boolean ehPrimeiraPagamentoMensalDe() {
		Calendar data = Calendar.getInstance();
		data.add(Calendar.DATE, -30);
		Long pagamentosDeResponsavelFinanceiroDesdeData = pagamentoDao.pagamentosDeResponsavelFinanceiroDesdeData(responsavelFinanceiro,  data);
		if(pagamentosDeResponsavelFinanceiroDesdeData>0){
			return false;
		}
		return true;
	}
	
	public Long quantidadePagamentosDesdeDiaDoMesTal(Integer diaDoMes) {
		Calendar hoje = Calendar.getInstance();
		Calendar dataAtrasada = new GregorianCalendar(hoje.get(Calendar.YEAR),hoje.get(Calendar.MONTH),diaDoMes,hoje.get(Calendar.HOUR),hoje.get(Calendar.MINUTE),hoje.get(Calendar.SECOND));
		return  pagamentoDao.pagamentosDeResponsavelFinanceiroDesdeData(responsavelFinanceiro,  dataAtrasada);
	}
	public boolean ehPrimeiraPagamentoDesdeDiaDoMesTal(Integer diaDoMes) {
		Long pagamentosDeResponsavelFinanceiroDesdeData = quantidadePagamentosDesdeDiaDoMesTal(diaDoMes);
		if(pagamentosDeResponsavelFinanceiroDesdeData>0){
			return false;
		}
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
}
