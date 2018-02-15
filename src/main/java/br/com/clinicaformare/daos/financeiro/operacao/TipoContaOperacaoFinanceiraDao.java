package br.com.clinicaformare.daos.financeiro.operacao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.TipoContaOperacaoFinanceira;

@Stateless
public class TipoContaOperacaoFinanceiraDao extends Dao<TipoContaOperacaoFinanceira> {
	@Inject
	EntityManager manager;

	public TipoContaOperacaoFinanceiraDao() {
		super(TipoContaOperacaoFinanceira.class);
	}

	public List<TipoContaOperacaoFinanceira> listarTipoContaOperacaoFinanceira() {
		TypedQuery<TipoContaOperacaoFinanceira> query = manager.createNamedQuery(TipoContaOperacaoFinanceira.LISTAR, TipoContaOperacaoFinanceira.class);
		return query.getResultList();
	}

	public TipoContaOperacaoFinanceira getTipoByTipoString(String tipo) {
		System.out.println("---" + "TipoContaOperacaoFinanceira" + "---:" + tipo);
		List<TipoContaOperacaoFinanceira> tiposContaOperacaoFinanceira = this.listarTipoContaOperacaoFinanceira();
		Optional<TipoContaOperacaoFinanceira> tiposOptional = tiposContaOperacaoFinanceira.stream().filter(f -> f.getTipo().equals(tipo)).findAny();
		TipoContaOperacaoFinanceira tipoConta = tiposOptional.get();
		return tipoConta;
	}

	public TipoContaOperacaoFinanceira getContaBancaria() {
		return this.getTipoByTipoString("Conta Bancária");
	}

	public TipoContaOperacaoFinanceira getContaMasterIugu() {
		return this.getTipoByTipoString("Conta Master Iugu");
	}

	public TipoContaOperacaoFinanceira getContaMasterItau() {
		return this.getTipoByTipoString("Conta Master Itaú");
	}

	public TipoContaOperacaoFinanceira getSubconta() {
		return this.getTipoByTipoString("Subconta");
	}

	public TipoContaOperacaoFinanceira getCartaoDeCredito() {
		return this.getTipoByTipoString("Cartão de Crédito");
	}

	public TipoContaOperacaoFinanceira getBoleto() {
		return this.getTipoByTipoString("Boleto");
	}
//	public TipoContaOperacaoFinanceira buscaPorTipo(String tipo) {
//	String jpql = "select tc from TipoContaOperacaoFinanceira tc where tc.tipo = :tipo";
//	TypedQuery<TipoContaOperacaoFinanceira> query = manager.createQuery(jpql, TipoContaOperacaoFinanceira.class);
//	query.setParameter("tipo", tipo);
//	return query.getSingleResult();
//}
}
