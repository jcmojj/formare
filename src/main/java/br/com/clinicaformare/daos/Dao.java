package br.com.clinicaformare.daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class Dao<T> {

	private final Class<T> classe;

	@Inject
	private EntityManager manager;


	public Dao(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) throws Exception{
		manager.persist(t);
		manager.flush();
	}
	
	public T adicionaVolta(T t) {
		manager.persist(t);
		return t;
	}

	public void remove(T t) {
		manager.joinTransaction();
		manager.remove(manager.merge(t));
	}

	public void atualiza(T t) {
		manager.joinTransaction();
		manager.merge(t);
	}

	public List<T> listaTodos() {
		System.out.println("Dentro de Dao de " +classe.getGenericSuperclass().toString());
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = manager.createQuery(query).getResultList();
		return lista;
	}

	public T buscaPorId(Long id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}

	public T buscaSomentId(Long id) {
		T instancia = manager.getReference(classe, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) manager.createQuery("select count(n) from " + classe.getName() + " n").getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
		return lista;
	}
	
	public EntityManager getEntityManager() {
		return this.manager;
	}
	
	public boolean isEmpty() {
		return this.contaTodos() > 0 ? false : true;
	}

	public Class<T> getClasse() {
		return classe;
	}
	
}