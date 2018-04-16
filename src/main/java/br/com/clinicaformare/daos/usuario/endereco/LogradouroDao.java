package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.Entidade;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.usuario.endereco.Logradouro;

@Stateless
@Entidade(tipo = TipoEntidade.LOGRADOURO)
public class LogradouroDao extends Dao<Logradouro> {

	public LogradouroDao() {
		super(Logradouro.class);
	}
	
	@Inject
	private EntityManager manager;
	
	public Logradouro buscaPorNome(String logradouro){
		String jpql = "select l from Logradouro l where l.nome = :logradouro";
		TypedQuery<Logradouro> query = this.manager.createQuery(jpql,Logradouro.class);
		query.setParameter("logradouro", logradouro);
		return query.getSingleResult();
	}
}
