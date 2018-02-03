package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.TipoProfissional;

@Stateless
public class TipoProfissionalDao extends Dao<TipoProfissional> {

	public TipoProfissionalDao() {
		super(TipoProfissional.class);
	}

	public TipoProfissional buscaPorTipo(String tipo) {
		String jpql = "select t from TipoProfissional t where t.tipo = :tipo";
		TypedQuery<TipoProfissional> query = super.getEntityManager().createQuery(jpql,TipoProfissional.class);
		query.setParameter("tipo", tipo);
		return query.getSingleResult();
	
	}
}