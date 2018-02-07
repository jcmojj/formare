package br.com.clinicaformare.daos.usuario;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Usuario;

@Stateless
public class UsuarioDao extends Dao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}

	public List<Usuario> listaTodosUsuarioClienteDoMaisNovoAoMaisVelho() {
		String jpql = "select u from Usuario u where u.equipe = false order by u.dataAlteracao desc";
		TypedQuery<Usuario> query = super.getEntityManager().createQuery(jpql,Usuario.class);
		return query.getResultList();
		
	}

	public List<Usuario> listaTodosUsuariosDoTipoSocia() {
		String jpql = "select u from Usuario u where u.socia > 0 order by u.nome desc";
		TypedQuery<Usuario> query = super.getEntityManager().createQuery(jpql,Usuario.class);
		return query.getResultList();
	}
}