package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.TipoSocia;

@Stateless
public class TipoSociaDao extends Dao<TipoSocia> {

	public TipoSociaDao() {
		super(TipoSocia.class);
	}

	public TipoSocia buscaPorTipo(String tipo) {
		String jpql = "select t from TipoSocia t where t.tipo = :tipo";
		TypedQuery<TipoSocia> query = super.getEntityManager().createQuery(jpql,TipoSocia.class);
		query.setParameter("tipo", tipo);
		return query.getSingleResult();
	
	}
//	public List<String> listaStringDeTiposProfissionalDeSocia() {
//		String jpql = "select tipoProfissional.tipo from TipoProfissional tipoProfissional" + " where tipoProfissional.deSocia is true ";
//		TypedQuery<String> query = super.getEntityManager().createQuery(jpql, String.class);
//		return query.getResultList();
//	}

//	public List<String> listaStringDeTiposDeProfissionalExistentes() {
//		String jpql = "select tipoProfissional.tipo from TipoProfissional tipoProfissional" + " join tipoProfissional.profissionais profissional "
//				+ " where tipoProfissional.deSocia is false ";
//		TypedQuery<String> query = super.getEntityManager().createQuery(jpql, String.class);
//		Set<String> set = new HashSet<>(query.getResultList());
//		List<String> list = new ArrayList<>(set);
//		return list;
//	}
}