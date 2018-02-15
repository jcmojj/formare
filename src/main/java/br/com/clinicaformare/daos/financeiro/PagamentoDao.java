package br.com.clinicaformare.daos.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.Pagamento;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;

@Stateless
public class PagamentoDao extends Dao<Pagamento> {
	EntityManager manager;

	public PagamentoDao() {
		super(Pagamento.class);
		manager = super.getEntityManager();
	}

	
	
	public Long pagamentosDeResponsavelFinanceiroDesdeData(ResponsavelFinanceiro responsavelFinanceiro, Calendar data) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Pagamento> p = cq.from(Pagamento.class);
		ParameterExpression<ResponsavelFinanceiro> rfPE = cb.parameter(ResponsavelFinanceiro.class);
		ParameterExpression<Calendar> cPE = cb.parameter(Calendar.class);
//		Where
		cq.where(
				cb.equal(p.get("responsavelFinanceiro"), rfPE),
				cb.greaterThan(p.get("dataCriacao"), cPE)
				);
//		Select
		Expression<Long> count = cb.count(p);
		cq.select(count);
//		Criando a Query
		TypedQuery<Long> query = manager.createQuery(cq);
//		Substituicao
		query.setParameter(rfPE, responsavelFinanceiro);
		query.setParameter(cPE, data);
		return query.getSingleResult();
		
// 		Exemplo
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Country> q = cb.createQuery(Country.class);
//		Root<Country> c = q.from(Country.class);
//		ParameterExpression<Integer> p = cb.parameter(Integer.class);
//		q.select(c).where(cb.gt(c.get("population"), p));
//		TypedQuery<Country> query = em.createQuery(q);
//		query.setParameter(p, 10000000);
//		List<Country> results = query.getResultList();
////		
////		cq.where(cb.lessThan(arg0, arg1)));
//		
//		
//		cq.select(cb.count(cq.from(Pagamento.class)));
//		
//
//		return manager.createQuery(cq).getSingleResult();
		
//		 CriteriaBuilder cb = em.getCriteriaBuilder();
//	        CriteriaQuery query1 = cb.createQuery();
//	        Root e = query1.from(Event.class);
//	        query1.select(e);
//	        query1.where(cb.lessThanOrEqualTo(e.get("c1"), e.get("c2")));
//	        List results = em.createQuery(query1).getResultList();
//	        System.out.println(results.size());
		
//		 CriteriaBuilder builder = super.getEntityManager().getCriteriaBuilder();
//		 
//	        CriteriaQuery<Pagamento> cq = builder.createQuery(Pagamento.class);
//	        		Root<Pagamento> pagamento = cq.from(Pagamento.class);
//	        		Expression count = builder.count(pagamento);
//	        		cq.select(count);
//			
////	        query.builder.count(root).select(pagamento);
//	        
////	        query.where(cb.lessThanOrEqualTo(e.get("c1"), e.get("c2")));
////	        List results = em.createQuery(query1).getResultList();
////	        System.out.println(results.size());
//		
//		
//		
////		Calendar hojeMenos30dias = Calendar.getInstance();
////		hojeMenos30dias.add(Calendar.DATE, -1);

		
//		CriteriaBuilder builder = super.getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
//
//		Root<Pagamento> root = criteria.from(Pagamento.class);
//		
//		Path<ResponsavelFinanceiro> path = root.<ResponsavelFinanceiro>get("responsavelFinanceiro");
//		Predicate equal = builder.equal(path, responsavelFinanceiro);
//		criteria.where(equal);
//		
//		Expression<Long> count = builder.count(root);
//		criteria.select(count);
		
		
//		Path<Calendar> path2 = root.<Calendar>get("dataCriacao").<String>get("Calendar.DAY_OF_MONTH");
		
//		Calendar data = Calendar.getInstance();
//		data.get
		
		
		
//		Expression soma = builder.lessThan(arg0, arg1)
		
//		Integer resultado = super.getEntityManager().createQuery(criteria).getSingleResult();
//		if(resultado > 0) {
//			return false;
//		}
//		 return true;
		
//		String jpql = "select COUNT(p) from Pagamento p where p.responsavelFinanceiro = :responsavelFinanceiro AND p.";
//		TypedQuery<Pagamento> query = super.getEntityManager().createQuery(jpql,Pagamento.class);
//		query.setParameter("responsavelFinanceiro", responsavelFinanceiro);
//		query.getMaxResults()
		
	}

	public BigDecimal faturamentoTotalDesdeDia(Integer diaDoMes) {
		Calendar hoje = Calendar.getInstance();
		Calendar dataAtrasada = new GregorianCalendar(hoje.get(Calendar.YEAR),hoje.get(Calendar.MONTH),diaDoMes,hoje.get(Calendar.HOUR),hoje.get(Calendar.MINUTE),hoje.get(Calendar.SECOND));
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Pagamento> p = cq.from(Pagamento.class);
		ParameterExpression<ResponsavelFinanceiro> rfPE = cb.parameter(ResponsavelFinanceiro.class);
		ParameterExpression<Calendar> cPE = cb.parameter(Calendar.class);
//		Where
		cq.where(
				cb.greaterThan(p.get("dataCriacao"), cPE)
				);
////		Select
//		Expression<BigDecimal> sum = p.sum();
//		cq.select(sum);
////		Criando a Query
//		TypedQuery<Long> query = manager.createQuery(cq);
////		Substituicao
//		query.setParameter(rfPE, responsavelFinanceiro);
//		query.setParameter(cPE, data);
//		return query.getSingleResult();
		
		return new BigDecimal(0);
		
	}
}