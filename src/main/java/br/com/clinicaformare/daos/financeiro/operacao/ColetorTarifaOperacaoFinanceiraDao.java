package br.com.clinicaformare.daos.financeiro.operacao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.ColetorTarifaOperacaoFinanceira;

@Stateless
public class ColetorTarifaOperacaoFinanceiraDao extends Dao<ColetorTarifaOperacaoFinanceira> {
	@Inject
	EntityManager manager;

	public ColetorTarifaOperacaoFinanceiraDao() {
		super(ColetorTarifaOperacaoFinanceira.class);
	}

	public List<ColetorTarifaOperacaoFinanceira> listarColetorTarifaOperacaoFinanceira() {
		TypedQuery<ColetorTarifaOperacaoFinanceira> query = manager.createNamedQuery(ColetorTarifaOperacaoFinanceira.LISTAR, ColetorTarifaOperacaoFinanceira.class);
		return query.getResultList();
	}

	public ColetorTarifaOperacaoFinanceira getColetorByColetorString(String nome) {
		System.out.println("---" + "ColetorTarifaOperacaoFinanceira" + "---:" + nome);
		List<ColetorTarifaOperacaoFinanceira> coletoresTarifaOperacaoFinanceira = this.listarColetorTarifaOperacaoFinanceira();
		Optional<ColetorTarifaOperacaoFinanceira> coletoresOptional = coletoresTarifaOperacaoFinanceira.stream().filter(c -> c.getNome().equals(nome)).findAny();
		ColetorTarifaOperacaoFinanceira coletor = coletoresOptional.get();
		return coletor;
	}

	public ColetorTarifaOperacaoFinanceira getImposto() {
		return this.getColetorByColetorString("Imposto");
	}

	public ColetorTarifaOperacaoFinanceira getIugu() {
		return this.getColetorByColetorString("Iugu");
	}

	public ColetorTarifaOperacaoFinanceira getCacaio() {
		return this.getColetorByColetorString("Cacaio");
	}

	// public ColetorTarifaOperacaoFinanceira buscaPorNome(String nome) {
	// String jpql = "select tc from ColetorTarifaOperacaoFinanceira tc where tc.nome = :nome";
	// TypedQuery<ColetorTarifaOperacaoFinanceira> query = manager.createQuery(jpql,ColetorTarifaOperacaoFinanceira.class);
	// query.setParameter("nome", nome);
	// return query.getSingleResult();
	// }

	// public List<TarifaOperacaoFinanceira> listarTarifaOperacaoFinanceira() {
	// TypedQuery<TarifaOperacaoFinanceira> query = manager.createNamedQuery(TarifaOperacaoFinanceira.LISTAR_TARIFAS, TarifaOperacaoFinanceira.class);
	// return query.getResultList();
	// }
}
