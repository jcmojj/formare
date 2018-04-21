package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.BasicUser;
import br.com.clinicaformare.model.usuario.Usuario;

@Stateless
public class BasicUserDao extends Dao<BasicUser> {

	@Inject
	EntityManager manager;
	
	
	public BasicUserDao() {
		super(BasicUser.class);
	}

	public Usuario usarioDeBasicUser(BasicUser basicUser) {
		System.out.println("BasicUserDao usarioDeBasicUser");
		String jpql = "select u from Usuario u where u.basicUser =:basicUser";
		TypedQuery<Usuario> query = manager.createQuery(jpql,Usuario.class);
		query.setParameter("basicUser", basicUser);
		return query.getSingleResult();
	}

//	public List<Usuario> buscaUsuariosComLoginEPassword(String email, String password) {
//		// todos usuarios com mesmo email precisam ter a mesma senha sempre
//		System.out.println("Procurando Usuarios com email: " + email +  " e password: ");
//		String jpql = "select u from Usuario u where u.email = " + ":email and u.password = :password order by u.dataCriacao desc";
//		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
//		query.setParameter("email", email);
//		query.setParameter("password", password);
//		query.getResultList().forEach(u -> System.out.println("Existe Usuario buscado: " + u));
//		return query.getResultList();
//	}

}