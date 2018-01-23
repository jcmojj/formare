package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.usuario.Usuario;

@Stateless
public class UsuarioDao extends Dao<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}
}