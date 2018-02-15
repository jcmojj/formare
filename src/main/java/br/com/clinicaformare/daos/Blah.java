package br.com.clinicaformare.daos;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Blah<T> extends Dao<T> {
//	private final Class<T> classe;
	private final EntityManager entityManager;
	CriteriaBuilder criteriaBuilder;// = entityManager.getCriteriaBuilder();
	CriteriaQuery<T> query;// = criteriaBuilder.createQuery();
	Root<T> root;
	Predicate whereClause;

	public Blah(Class<T> classe) {
		super(classe);
//		this.classe = classe;
		this.entityManager = super.getEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();
		query = criteriaBuilder.createQuery(classe);
		whereClause = criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		root = query.from(classe);
	}

	
	// CriteriaBuilder criteriaBuilder;// = entityManager.getCriteriaBuilder();
	// CriteriaQuery query;// = criteriaBuilder.createQuery();
	// Root<Entity> root;
	// Predicate whereClause;
	// EntityManager entityManager;
	//
	//
//	CriteriaBuilder cb = em.getCriteriaBuilder();
//	CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//	cq.select(cb.count(cq.from(MyEntity.class)));
//
//	return em.createQuery(cq).getSingleResult();
	
//	 public CriteriaQuery<T> getQuery() {
//	 query.select(root);
//	 query.where(whereClause);
//	 return query;
//	 }
//	
//	
//	
//	 public CriteriaQuery<Long> getQueryForCount() {
//	 query.select(criteriaBuilder.count(root));
//	 query.where(whereClause);
//	 return query;
//	 }
//	
//	 public List<T> list() {
//	 TypedQuery<T> q = this.entityManager.createQuery(this.getQuery());
//	 return q.getResultList();
//	 }
//	
//	 public Long count() {
//	 TypedQuery<Long> q = this.entityManager.createQuery(this.getQueryForCount());
//	 return q.getSingleResult();
//	 }
}
