package br.com.clinicaformare.daos.usuario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Usuario;

@Stateless
public class UsuarioDao extends Dao<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager manager;

	public UsuarioDao() {
		super(Usuario.class);
		// this.manager = super.getEntityManager();
	}

	public boolean existe(Usuario usuario) {
		System.out.println("Procurando Usuario: " + usuario);
		String jpql = "select u from Usuario u where u.email = " + ":email and u.password = :password";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("password", usuario.getPassword());
		query.getResultList().forEach(u -> System.out.println("Existe Usuario: " + u));
		return !query.getResultList().isEmpty();
	}
	
	public Usuario buscaLoginPassword(Usuario usuario) {
		System.out.println("Buscando Usuario: " + usuario);
		String jpql = "select u from Usuario u where u.email = " + ":email and u.password = :password";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("password", usuario.getPassword());
		query.getResultList().forEach(u -> System.out.println("Existe Usuario buscado: " + u));
		return query.getSingleResult();
	}


	public List<Usuario> listaTodosUsuarioClienteDoMaisNovoAoMaisVelho() {
		String jpql = "select u from Usuario u where u.equipe = false order by u.dataAlteracao desc";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		return query.getResultList();

	}

	public List<Usuario> listaTodosUsuariosDoTipoSocia() {
		String jpql = "select u from Usuario u where u.socia > 0 order by u.nome desc";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}

	public List<Usuario> listaUsuarioComTipoProfissionalEEspecialidade(String tipoProfissionalString, boolean especialista) {
		String jpql = "select usuario from Usuario usuario where usuario.profissional.tipoProfissional.tipo = :tipoProfissional"
				+ " and usuario.profissional.tipoProfissional.especialista = :especialista" + " order by usuario.nome asc";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("tipoProfissional", tipoProfissionalString);
		query.setParameter("especialista", especialista);
		return query.getResultList();
	}

//	public List<Usuario> listaTodosUsuariosSociaDoTipo(TipoSocia tipoSocia) {
//		// String jpql = "select u from Usuario u join u.socia.tiposSocia tipo where tipo = :tipoSocia ";
//		String jpql = "select s.usuario from Socia s join s.tiposSocia tipoSocia where tipoSocia = :tipoSocia ";
//		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
//		query.setParameter("tipoSocia", tipoSocia);
//		return query.getResultList();
//	}

}