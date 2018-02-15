package br.com.clinicaformare.util;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.clinicaformare.daos.financeiro.operacao.OperadorFinanceiroDao;
import br.com.clinicaformare.daos.financeiro.operacao.TarifaOperacaoFinanceiraDao;
import br.com.clinicaformare.model.financeiro.operador.ColetorTarifaOperacaoFinanceira;
import br.com.clinicaformare.model.financeiro.operador.OperadorFinanceiro;

@Named
@ApplicationScoped
public class CalculadoraParametro {
	
	
	public CalculadoraParametro() {
		super();
	}

	@Inject
	private OperadorFinanceiroDao operadorFinanceiroDao;
	@Inject
	private TarifaOperacaoFinanceiraDao tarifaOperacaoFinanceiraDao;

	
	
	
	// Operador Financeiro
	public OperadorFinanceiro getOperadorFinanceiro() {
		return operadorFinanceiroDao.getOperadorFinanceiroPadrao();
	}
	/*
	 * 
	 * TARIFAS EXISTENTES NO SISTEMA
	 * 
	 */
	
	// Tarifa Entre Subcontas Iugu
	public BigDecimal todasTarifasFixaInternaEntreSubContasIugu() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaInternaEntreSubContasIugu();
	}
	
	public BigDecimal tarifaFixaInternaEntreSubContasIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubContasIugu(coletorTarifa);
	}
	public BigDecimal todasTarifasPorcentagemInternaEntreSubContasIugu() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemInternaEntreSubContasIugu();
	}
	
	public BigDecimal tarifaPorcentagemInternaEntreSubContasIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemInternaEntreSubContasIugu(coletorTarifa);
	}
	// Tarifa Entre Conta Master e Subcontas Iugu

	public BigDecimal todasTarifasFixaInternaEntreMasterIuguESubcontaIugu() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaInternaEntreMasterIuguESubcontaIugu();
	}

	public BigDecimal tarifaFixaInternaEntreMasterIuguESubcontaIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreMasterIuguESubcontaIugu(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemInternaEntreMasterIuguESubcontaIugu() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemInternaEntreMasterIuguESubcontaIugu();
	}

	public BigDecimal tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu(coletorTarifa);
	}
	// Tarifa Entre Subconta e Conta Master Iugu
	public BigDecimal todasTarifasFixaInternaEntreSubcontaIuguEMasterIugu() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaInternaEntreSubcontaIuguEMasterIugu();
	}

	public BigDecimal tarifaFixaInternaEntreSubcontaIuguEMasterIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaInternaEntreSubcontaIuguEMasterIugu(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemInternaEntreSubcontaIuguEMasterIugu() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemInternaEntreSubcontaIuguEMasterIugu();
	}

	public BigDecimal tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu(coletorTarifa);
	}
	// Tarifa de Saque Subconta - Conta Bancária
	public BigDecimal todasTarifasFixaSaqueSubcontaContaBancaria() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaSaqueSubcontaContaBancaria() ;
	}

	public BigDecimal tarifaFixaSaqueSubcontaContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaSaqueSubcontaContaBancaria(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemSaqueSubcontaContaBancaria() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemSaqueSubcontaContaBancaria();
	}

	public BigDecimal tarifaPorcentagemSaqueSubcontaContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemSaqueSubcontaContaBancaria(coletorTarifa);
	}
	// Tarifa de Saque Conta Master - Conta Bancária
	public BigDecimal todasTarifasFixaSaqueContaMasterIuguContaBancaria() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaSaqueContaMasterIuguContaBancaria();
	}

	public BigDecimal tarifaFixaSaqueContaMasterIuguContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaSaqueContaMasterIuguContaBancaria(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemSaqueContaMasterIuguContaBancaria() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemSaqueContaMasterIuguContaBancaria();
	}

	public BigDecimal tarifaPorcentagemSaqueContaMasterIuguContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemSaqueContaMasterIuguContaBancaria(coletorTarifa);
	}
	
	
	
	// Tarifa de Deposito na Conta Master Iugu - Cartão de Crédito
	public BigDecimal todasTarifasFixaDepositoCartaoDeCredito() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaDepositoCartaoDeCredito();
	}

	public BigDecimal tarifaFixaDepositoCartaoDeCredito(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaDepositoCartaoDeCredito(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoCartaoDeCredito() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemDepositoCartaoDeCredito();
	}

	public BigDecimal tarifaPorcentagemDepositoCartaoDeCredito(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemDepositoCartaoDeCredito(coletorTarifa);
	}
	// Tarifa de Deposito na Conta Master Iugu - Boleto

	public BigDecimal todasTarifasFixaDepositoBoleto() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaDepositoBoleto();
	}

	public BigDecimal tarifaFixaDepositoBoleto(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaDepositoBoleto(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoBoleto() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemDepositoBoleto();
	}

	public BigDecimal tarifaPorcentagemDepositoBoleto(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemDepositoBoleto(coletorTarifa);
	}

	public BigDecimal todasTarifasFixaDepositoContaBancariaNoItau() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaDepositoContaBancariaNoItau();
	}

	public BigDecimal tarifaFixaDepositoContaBancariaNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaDepositoContaBancariaNoItau(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoContaBancariaNoItau() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemDepositoContaBancariaNoItau();
	}

	public BigDecimal tarifaPorcentagemDepositoContaBancariaNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemDepositoContaBancariaNoItau(coletorTarifa);
	}

	public BigDecimal todasTarifasFixaDepositoBoletoNoItau() {
		return tarifaOperacaoFinanceiraDao.todasTarifasFixaDepositoBoletoNoItau();
	}

	public BigDecimal tarifaFixaDepositoBoletoNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaFixaDepositoBoletoNoItau(coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoBoletoNoItau() {
		return tarifaOperacaoFinanceiraDao.todasTarifasPorcentagemDepositoBoletoNoItau();
	}

	public BigDecimal tarifaPorcentagemDepositoBoletoNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		return tarifaOperacaoFinanceiraDao.tarifaPorcentagemDepositoBoletoNoItau(coletorTarifa);
	}
}
