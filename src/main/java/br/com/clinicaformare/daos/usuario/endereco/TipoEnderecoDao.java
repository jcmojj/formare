package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Stateless
public class TipoEnderecoDao extends Dao<TipoEndereco> {

	public TipoEnderecoDao() {
		super(TipoEndereco.class);
	}
	
	@Inject
	private EntityManager manager;
	
	public TipoEndereco buscaPorNome(String tipo){
		String jpql = "select te from TipoEndereco te where te.tipo = :tipo";
		TypedQuery<TipoEndereco> query = this.manager.createQuery(jpql,TipoEndereco.class);
		query.setParameter("tipo", tipo);
		return query.getSingleResult();
	}
}
