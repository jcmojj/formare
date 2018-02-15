package br.com.clinicaformare.daos.financeiro.operacao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.OperadorFinanceiro;
import br.com.clinicaformare.model.financeiro.operador.ParametroFormare;

@Stateless
public class ParametroFormareDao extends Dao<ParametroFormare> {
	EntityManager manager;
	public ParametroFormareDao() {
		super(ParametroFormare.class);
		this.manager = super.getEntityManager();
	}
	
//	public OperadorFinanceiro getOperadorFinanceiro() {
//		String jpql = "select operador from ParametroFormare parametro where parametro =:operadorFinanceiro";
//		TypedQuery<String> query = manager.createNamedQuery(jpql,String.class);
//		String operadorFinanceiro = "OperadorFinanceiro";
//		query.setParameter("operadorFinanceiro", operadorFinanceiro);
//		String stringOF = query.getSingleResult();
//		
//		jpql = "select operador from OperadorFinanceiro operador where operador =:stringOF ";
//		TypedQuery<OperadorFinanceiro> query2 = manager.createNamedQuery(jpql, OperadorFinanceiro.class);
//		query2.setParameter("stringOF", stringOF);
//		return query2.getSingleResult();
//	}
}