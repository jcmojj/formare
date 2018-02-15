package br.com.clinicaformare.daos.financeiro.operacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.ColetorTarifaOperacaoFinanceira;
import br.com.clinicaformare.model.financeiro.operador.FormaTransferenciaOperacaoFinanceira;
import br.com.clinicaformare.model.financeiro.operador.OperadorFinanceiro;
import br.com.clinicaformare.model.financeiro.operador.TarifaOperacaoFinanceira;
import br.com.clinicaformare.model.financeiro.operador.TipoContaOperacaoFinanceira;
import br.com.clinicaformare.model.financeiro.operador.TipoTarifaOperacaoFinanceira;
import br.com.clinicaformare.util.CalculadoraParametro;

@Stateless
public class TarifaOperacaoFinanceiraDao extends Dao<TarifaOperacaoFinanceira> {
	@Inject
	EntityManager manager;
	@Inject
	CalculadoraParametro calculadora;
	@Inject
	private OperadorFinanceiroDao operadorFinanceiroDao;
	@Inject
	private FormaTransferenciaOperacaoFinanceiraDao formaTransferenciaDao;
	@Inject
	private TipoContaOperacaoFinanceiraDao tipoContaDao;
	@Inject
	private TipoTarifaOperacaoFinanceiraDao tipoTarifaDao;

	public TarifaOperacaoFinanceiraDao() {
		super(TarifaOperacaoFinanceira.class);
	}

	public List<TarifaOperacaoFinanceira> listarTarifaOperacaoFinanceira() {
		TypedQuery<TarifaOperacaoFinanceira> query = manager.createNamedQuery(TarifaOperacaoFinanceira.LISTAR_TARIFAS, TarifaOperacaoFinanceira.class);
		return query.getResultList();
	}

	public BigDecimal getTarifa(	OperadorFinanceiro operadorFinanceiro,
							 FormaTransferenciaOperacaoFinanceira formaTransferencia,
							 TipoContaOperacaoFinanceira tipoContaOrigem,
							 TipoContaOperacaoFinanceira tipoContaDestino,
							 TipoTarifaOperacaoFinanceira tipoTarifa,
							 ColetorTarifaOperacaoFinanceira coletorTarifa) {
							List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceira = listarTarifaOperacaoFinanceira();
							Optional<TarifaOperacaoFinanceira> tarifasOptional= tarifasOperacaoFinanceira.stream()
							.filter(t -> t.getOperadorFinanceiro() ==operadorFinanceiro)
							.filter(t -> t.getFormaTransferencia() == formaTransferencia)
							.filter(t -> t.getTipoContaOrigem() == tipoContaOrigem)
							.filter(t -> t.getTipoContaDestino() == tipoContaDestino)
							.filter(t -> t.getTipoTarifa() == tipoTarifa)
							.filter(t -> t.getColetorTarifa() == coletorTarifa)
							.findAny();
							TarifaOperacaoFinanceira tarifa = tarifasOptional.get();
		return tarifa.getValor();
	}
	
	public BigDecimal getTarifaFromAllColetor(OperadorFinanceiro operadorFinanceiro,
							 FormaTransferenciaOperacaoFinanceira formaTransferencia,
							 TipoContaOperacaoFinanceira tipoContaOrigem,
							 TipoContaOperacaoFinanceira tipoContaDestino,
							 TipoTarifaOperacaoFinanceira tipoTarifa) {
								List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceira = listarTarifaOperacaoFinanceira();
								Double total = tarifasOperacaoFinanceira.stream()
							.filter(t -> t.getOperadorFinanceiro() ==operadorFinanceiro)
							.filter(t -> t.getFormaTransferencia() == formaTransferencia)
							.filter(t -> t.getTipoContaOrigem() == tipoContaOrigem)
							.filter(t -> t.getTipoContaDestino() == tipoContaDestino)
							.filter(t -> t.getTipoTarifa() == tipoTarifa)
							.mapToDouble(t -> t.getValor().doubleValue())
							.sum();
							return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
	}


	// Tarifa Entre Subcontas Iugu
	public BigDecimal todasTarifasFixaInternaEntreSubContasIugu() {
		System.out.println("---"+"todasTarifasFixaInternaEntreSubContasIugu"+"---");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getSubcontas(),
								tipoContaDao.getSubconta(),
								tipoContaDao.getSubconta(),
								tipoTarifaDao.getFixa());
	}

	public BigDecimal tarifaFixaInternaEntreSubContasIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("---"+"tarifaFixaInternaEntreSubContasIugu"+"---");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getSubcontas(),
								tipoContaDao.getSubconta(),
								tipoContaDao.getSubconta(),
								tipoTarifaDao.getFixa(),
								coletorTarifa);
		
	}

	public BigDecimal todasTarifasPorcentagemInternaEntreSubContasIugu() {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa ";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getSubcontas());
		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
		query.setParameter("tipoContaDestino", tipoContaDao.getSubconta());
		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
		return query.getSingleResult();
	}

	public BigDecimal tarifaPorcentagemInternaEntreSubContasIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
//		List<TarifaOperacaoFinanceira> tarifas = this.listarTarifaOperacaoFinanceira();

		 String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
		 + "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
		 + "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
		 TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		 query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		 query.setParameter("formaTransferencia", formaTransferenciaDao.getSubcontas());
		 query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
		 query.setParameter("tipoContaDestino", tipoContaDao.getSubconta());
		 query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
		 query.setParameter("coletorTarifa", coletorTarifa);
		 return query.getSingleResult();
	}

	// Tarifa Entre Conta Master e Subcontas Iugu
	public BigDecimal todasTarifasFixaInternaEntreMasterIuguESubcontaIugu() {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa ";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getSplit());
		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoContaDestino", tipoContaDao.getSubconta());
		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
		return query.getSingleResult();
	}

	public BigDecimal tarifaFixaInternaEntreMasterIuguESubcontaIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getSplit());
		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoContaDestino", tipoContaDao.getSubconta());
		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
		query.setParameter("coletorTarifa", coletorTarifa);
		return query.getSingleResult();
	}

	public BigDecimal todasTarifasPorcentagemInternaEntreMasterIuguESubcontaIugu() {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa ";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getSplit());
		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoContaDestino", tipoContaDao.getSubconta());
		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
		return query.getSingleResult();
	}

	public BigDecimal tarifaPorcentagemInternaEntreMasterIuguESubcontaIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getSplit());
		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoContaDestino", tipoContaDao.getSubconta());
		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
		query.setParameter("coletorTarifa", coletorTarifa);
		return query.getSingleResult();
	}

	// Tarifa Entre Subconta e Conta Master Iugu
	public BigDecimal todasTarifasFixaInternaEntreSubcontaIuguEMasterIugu() {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa ";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getExtorno());
		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
		return query.getSingleResult();
	}

	public BigDecimal tarifaFixaInternaEntreSubcontaIuguEMasterIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getExtorno());
		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
		query.setParameter("coletorTarifa", coletorTarifa);
		return query.getSingleResult();
	}

	public BigDecimal todasTarifasPorcentagemInternaEntreSubcontaIuguEMasterIugu() {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa ";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getExtorno());
		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
		return query.getSingleResult();
	}

	public BigDecimal tarifaPorcentagemInternaEntreSubcontaIuguEMasterIugu(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
		query.setParameter("formaTransferencia", formaTransferenciaDao.getExtorno());
		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterIugu());
		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
		query.setParameter("coletorTarifa", coletorTarifa);
		return query.getSingleResult();
	}

	// Tarifa de Saque Subconta - Conta Bancária
	public BigDecimal todasTarifasFixaSaqueSubcontaContaBancaria() {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa ";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaBancaria());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
//		return query.getSingleResult();
		System.out.println("[---"+"todasTarifasFixaDepositoCartaoDeCredito"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getSaque(),
								tipoContaDao.getSubconta(),
								tipoContaDao.getContaBancaria(),
								tipoTarifaDao.getFixa());
	}

	public BigDecimal tarifaFixaSaqueSubcontaContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaBancaria());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
//		query.setParameter("coletorTarifa", coletorTarifa);
//		return query.getSingleResult();
		System.out.println("[---"+"tarifaFixaDepositoCartaoDeCredito"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getSaque(),
								tipoContaDao.getSubconta(),
								tipoContaDao.getContaBancaria(),
								tipoTarifaDao.getFixa(),
								coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemSaqueSubcontaContaBancaria() {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa ";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaBancaria());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
//		return query.getSingleResult();
		System.out.println("[---"+"todasTarifasPorcentagemDepositoCartaoDeCredito"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getSaque(),
								tipoContaDao.getSubconta(),
								tipoContaDao.getContaBancaria(),
								tipoTarifaDao.getPorcentagem());
	}

	public BigDecimal tarifaPorcentagemSaqueSubcontaContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getSubconta());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaBancaria());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
//		query.setParameter("coletorTarifa", coletorTarifa);
//		return query.getSingleResult();
		System.out.println("[---"+"tarifaPorcentagemDepositoCartaoDeCredito"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getSaque(),
								tipoContaDao.getSubconta(),
								tipoContaDao.getContaBancaria(),
								tipoTarifaDao.getPorcentagem(),
								coletorTarifa);
	}

	// Tarifa de Saque Conta Master - Conta Master Itau
	public BigDecimal todasTarifasFixaSaqueContaMasterIuguContaBancaria() {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa ";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterItau());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
//		return query.getSingleResult();
		
	}

	public BigDecimal tarifaFixaSaqueContaMasterIuguContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterItau());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getFixa());
//		query.setParameter("coletorTarifa", coletorTarifa);
//		return query.getSingleResult();
		
	}

	public BigDecimal todasTarifasPorcentagemSaqueContaMasterIuguContaBancaria() {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa ";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterItau());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
//		return query.getSingleResult();
		
	}

	public BigDecimal tarifaPorcentagemSaqueContaMasterIuguContaBancaria(ColetorTarifaOperacaoFinanceira coletorTarifa) {
//		String jpql = "select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
//				+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
//				+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa";
//		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
//		query.setParameter("operadorFinanceiro", operadorFinanceiroDao.getOperadorFinanceiroPadrao());
//		query.setParameter("formaTransferencia", formaTransferenciaDao.getSaque());
//		query.setParameter("tipoContaOrigem", tipoContaDao.getContaMasterIugu());
//		query.setParameter("tipoContaDestino", tipoContaDao.getContaMasterItau());
//		query.setParameter("tipoTarifa", tipoTarifaDao.getPorcentagem());
//		query.setParameter("coletorTarifa", coletorTarifa);
//		return query.getSingleResult();
		
	}

	// Tarifa de Deposito na Conta Master Iugu - Cartão de Crédito
	public BigDecimal todasTarifasFixaDepositoCartaoDeCredito() {
		System.out.println("[---"+"todasTarifasFixaDepositoCartaoDeCredito"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getCartaoDeCredito(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getFixa());
	}

	public BigDecimal tarifaFixaDepositoCartaoDeCredito(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaFixaDepositoCartaoDeCredito"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getCartaoDeCredito(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getFixa(),
								coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoCartaoDeCredito() {
		System.out.println("[---"+"todasTarifasPorcentagemDepositoCartaoDeCredito"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getCartaoDeCredito(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getPorcentagem());
	}

	public BigDecimal tarifaPorcentagemDepositoCartaoDeCredito(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaPorcentagemDepositoCartaoDeCredito"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getCartaoDeCredito(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getPorcentagem(),
								coletorTarifa);
	}

	// Tarifa de Deposito na Conta Master Iugu - Boleto
	public BigDecimal todasTarifasFixaDepositoBoleto() {
		System.out.println("[---"+"todasTarifasFixaDepositoBoleto"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getFixa());
	}

	public BigDecimal tarifaFixaDepositoBoleto(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaFixaDepositoBoleto"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getFixa(),
								coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoBoleto() {
		System.out.println("[---"+"todasTarifasPorcentagemDepositoBoleto"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getPorcentagem());
	}

	public BigDecimal tarifaPorcentagemDepositoBoleto(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaPorcentagemDepositoBoleto"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getOperadorFinanceiroPadrao(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterIugu(),
								tipoTarifaDao.getPorcentagem(),
								coletorTarifa);
	}

	// Tarifa de Deposito na Conta Master Itau - Transferencia Bancaria
	public BigDecimal todasTarifasFixaDepositoContaBancariaNoItau() {
		System.out.println("[---"+"todasTarifasFixaDepositoContaBancariaNoItau"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getContaBancaria(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getFixa());
	}

	public BigDecimal tarifaFixaDepositoContaBancariaNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaFixaDepositoContaBancariaNoItau"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getContaBancaria(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getFixa(),
								coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoContaBancariaNoItau() {
		System.out.println("[---"+"todasTarifasPorcentagemDepositoContaBancariaNoItau"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getContaBancaria(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getPorcentagem());
	}

	public BigDecimal tarifaPorcentagemDepositoContaBancariaNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaPorcentagemDepositoContaBancariaNoItau"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getContaBancaria(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getPorcentagem(),
								coletorTarifa);
	}

	// Tarifa de Deposito na Conta Master Itau - Boleto
//	public TarifaOperacaoFinanceira DepositoBoletoNoItau() {
//		return getTarifaOperacionalFinanceira()
//	}
	public BigDecimal todasTarifasFixaDepositoBoletoNoItau() {
		System.out.println("[---"+"todasTarifasFixaDepositoBoletoNoItau"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getFixa());
	}

	public BigDecimal tarifaFixaDepositoBoletoNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		System.out.println("[---"+"tarifaFixaDepositoBoletoNoItau"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getFixa(),
								coletorTarifa);
	}

	public BigDecimal todasTarifasPorcentagemDepositoBoletoNoItau() {
		System.out.println("[---"+"todasTarifasPorcentagemDepositoBoletoNoItau"+"---]");
		return this.getTarifaFromAllColetor(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getPorcentagem());
	}

	public BigDecimal tarifaPorcentagemDepositoBoletoNoItau(ColetorTarifaOperacaoFinanceira coletorTarifa) {		
		System.out.println("[---"+"tarifaPorcentagemDepositoBoletoNoItau"+"---]");
		return this.getTarifa(	operadorFinanceiroDao.getItau(),
								formaTransferenciaDao.getDeposito(),
								tipoContaDao.getBoleto(),
								tipoContaDao.getContaMasterItau(),
								tipoTarifaDao.getPorcentagem(),
								coletorTarifa);
	}

}