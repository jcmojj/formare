package br.com.clinicaformare.daos.financeiro.operacao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.FormaTransferenciaOperacaoFinanceira;

@Stateless
public class FormaTransferenciaOperacaoFinanceiraDao extends Dao<FormaTransferenciaOperacaoFinanceira> {
	@Inject
	EntityManager manager;

	public FormaTransferenciaOperacaoFinanceiraDao() {
		super(FormaTransferenciaOperacaoFinanceira.class);
	}

	public List<FormaTransferenciaOperacaoFinanceira> listarFormaTransferenciaOperacaoFinanceira() {
		TypedQuery<FormaTransferenciaOperacaoFinanceira> query = manager.createNamedQuery(FormaTransferenciaOperacaoFinanceira.LISTAR, FormaTransferenciaOperacaoFinanceira.class);
		return query.getResultList();
	}

	public FormaTransferenciaOperacaoFinanceira getFormaByFormaString(String forma) {
		System.out.println("---" + "FormaTransferenciaOperacaoFinanceira" + "---:" + forma);
		List<FormaTransferenciaOperacaoFinanceira> formasTransferenciaOperacaoFinanceira = this.listarFormaTransferenciaOperacaoFinanceira();
		Optional<FormaTransferenciaOperacaoFinanceira> formasOptional = formasTransferenciaOperacaoFinanceira.stream().filter(f -> f.getForma().equals(forma)).findAny();
		FormaTransferenciaOperacaoFinanceira formaTransferencia = formasOptional.get();
		return formaTransferencia;
	}

	public FormaTransferenciaOperacaoFinanceira getSplit() {
		return this.getFormaByFormaString("Split");
	}

	public FormaTransferenciaOperacaoFinanceira getExtorno() {
		return this.getFormaByFormaString("Extorno");
	}

	public FormaTransferenciaOperacaoFinanceira getSubcontas() {
		return this.getFormaByFormaString("Subcontas");
	}

	public FormaTransferenciaOperacaoFinanceira getSaque() {
		return this.getFormaByFormaString("Saque");
	}

	public FormaTransferenciaOperacaoFinanceira getDeposito() {
		return this.getFormaByFormaString("Depósito");
	}
//	public FormaTransferenciaOperacaoFinanceira buscaPorForma(String forma) {
//	String jpql = "select ft from FormaTransferenciaOperacaoFinanceira ft where ft.forma = :forma";
//	TypedQuery<FormaTransferenciaOperacaoFinanceira> query = manager.createQuery(jpql, FormaTransferenciaOperacaoFinanceira.class);
//	query.setParameter("forma", forma);
//	return query.getSingleResult();
//}

}
