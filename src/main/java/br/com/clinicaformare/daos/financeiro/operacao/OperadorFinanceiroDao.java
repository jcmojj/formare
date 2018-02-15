package br.com.clinicaformare.daos.financeiro.operacao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.OperadorFinanceiro;

@Stateless
public class OperadorFinanceiroDao extends Dao<OperadorFinanceiro> {
	@Inject
	EntityManager manager;

	public OperadorFinanceiroDao() {
		super(OperadorFinanceiro.class);
	}

	public List<OperadorFinanceiro> listarOperadorFinanceiro() {
		TypedQuery<OperadorFinanceiro> query = manager.createNamedQuery(OperadorFinanceiro.LISTAR, OperadorFinanceiro.class);
		return query.getResultList();
	}

	public OperadorFinanceiro getOperadorByOperadorString(String operador) {
		System.out.println("---" + "OperadorFinanceiro" + "---:" + operador);
		List<OperadorFinanceiro> operadoresFinanceiro = this.listarOperadorFinanceiro();
		Optional<OperadorFinanceiro> operadoresOptional = operadoresFinanceiro.stream().filter(f -> f.getNome().equals(operador)).findAny();
		OperadorFinanceiro operadorFinanceiro = operadoresOptional.get();
		return operadorFinanceiro;
	}

	public OperadorFinanceiro getOperadorFinanceiroPadrao() {

		
		List<OperadorFinanceiro> operadoresFinanceiro = this.listarOperadorFinanceiro();
		Optional<OperadorFinanceiro> operadoresOptional = operadoresFinanceiro.stream()
				// .filter(f -> f.getNome().equals(operador))
				.findAny();
		OperadorFinanceiro operadorFinanceiro = operadoresOptional.get();
		System.out.println("---" + "getOperadorFinanceiroPadrao" + "---:"+operadorFinanceiro.getNome());
		return operadorFinanceiro;
		// String jpql = "select op from OperadorFinanceiro op where op.operadorFinanceiroPadrao is true";
		// TypedQuery<OperadorFinanceiro> query = this.manager.createQuery(jpql, OperadorFinanceiro.class);
		// return query.getSingleResult();
	}

	public OperadorFinanceiro getIugu() {
		return this.getOperadorByOperadorString("Iugu");
	}

	public OperadorFinanceiro getItau() {
		return this.getOperadorByOperadorString("Itaú");
	}
	// public OperadorFinanceiro buscaPorNome(String operadorFinanceiroString) {
	// String jpql = "select op from OperadorFinanceiro op where op.nome = :nome";
	// TypedQuery<OperadorFinanceiro> query = this.manager.createQuery(jpql, OperadorFinanceiro.class);
	// query.setParameter("nome", operadorFinanceiroString);
	// return query.getSingleResult();
	// }

	// public OperadorFinanceiro buscaPorNome(String operadorString) {
	// System.out.println("nome1:-" + operadorString + "-");
	// String jpql = "select o.nome from OperadorFinanceiro o";//where o.nome = :operator";
	// Query query = this.manager.createQuery(jpql);
	// System.out.println("nome2: " + operadorString);
	//// query.setParameter("operator", operadorString);
	// System.out.println("nome3: " + operadorString);
	// List<String> lista = query.getResultList();
	// lista.forEach(a -> System.out.println("----->"+a));
	//
	//// return lista.get(1);
	// }
}
