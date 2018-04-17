package br.com.clinicaformare.dao.acesso;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.Acesso;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.model.acesso.TipoUsuario;
import br.com.clinicaformare.model.usuario.Usuario;

@Stateless
public class AcessoDao extends Dao<Acesso> {

	public AcessoDao() {
		super(Acesso.class);
	}
	
	@Inject
	EntityManager manager;

	public Acesso buscaAcessoPadrao(TipoEntidade tipoEntidade, TipoUsuario tipoUsuario) {
		System.out.println("Procurando Acesso para: " + tipoEntidade + " e "+tipoUsuario);
		String jpql = "select a from Acesso a where a.tipoEntidade = " + ":tipoEntidade and a.tipoUsuario = :tipoUsuario";
		TypedQuery<Acesso> query = manager.createQuery(jpql, Acesso.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("tipoUsuario", tipoUsuario);
		query.getResultList().forEach(a -> System.out.println("Existe Acesso: " + a));
		return query.getSingleResult();
	}
	
	public Boolean buscaAcessoInicializarPara(Usuario usuario, TipoEntidade tipoEntidade) {
		System.out.println("Procurando Acesso para: " + tipoEntidade + " e "+usuario);
		String jpql = "select (a.inicializar) from Acesso a where a.tipoEntidade = :tipoEntidade "
				+ "and a.usuario = :usuario";
		TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("usuario", usuario);
		query.getResultList().forEach(r -> System.out.println("Inicializar: " + r));
		return query.getSingleResult();
	}
}
