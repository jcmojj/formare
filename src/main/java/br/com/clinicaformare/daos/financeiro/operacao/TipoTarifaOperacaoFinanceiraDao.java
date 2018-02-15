package br.com.clinicaformare.daos.financeiro.operacao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.TipoTarifaOperacaoFinanceira;

@Stateless
public class TipoTarifaOperacaoFinanceiraDao extends Dao<TipoTarifaOperacaoFinanceira> {
	@Inject
	EntityManager manager;

	public TipoTarifaOperacaoFinanceiraDao() {
		super(TipoTarifaOperacaoFinanceira.class);
	}

	public List<TipoTarifaOperacaoFinanceira> listarTipoTarifaOperacaoFinanceira() {
		TypedQuery<TipoTarifaOperacaoFinanceira> query = manager.createNamedQuery(TipoTarifaOperacaoFinanceira.LISTAR, TipoTarifaOperacaoFinanceira.class);
		return query.getResultList();
	}

	public TipoTarifaOperacaoFinanceira getTipoByTipoString(String forma) {
		System.out.println("---" + "TipoTarifaOperacaoFinanceira" + "---:" + forma);
		List<TipoTarifaOperacaoFinanceira> tiposTarifaOperacaoFinanceira = this.listarTipoTarifaOperacaoFinanceira();
		Optional<TipoTarifaOperacaoFinanceira> tiposOptional = tiposTarifaOperacaoFinanceira.stream().filter(f -> f.getTipo().equals(forma)).findAny();
		TipoTarifaOperacaoFinanceira tipoTarifa = tiposOptional.get();
		return tipoTarifa;
	}

	public TipoTarifaOperacaoFinanceira getFixa() {
		return this.getTipoByTipoString("Fixa");
	}

	public TipoTarifaOperacaoFinanceira getPorcentagem() {
		return this.getTipoByTipoString("Porcentagem");
	}
//	public TipoTarifaOperacaoFinanceira buscaPortipo(String tipo) {
//	String jpql = "select tt from TipoTarifaOperacaoFinanceira tt where tt.tipo = :tipo";
//	TypedQuery<TipoTarifaOperacaoFinanceira> query = manager.createQuery(jpql, TipoTarifaOperacaoFinanceira.class);
//	query.setParameter("tipo", tipo);
//	return query.getSingleResult();
//}
}
