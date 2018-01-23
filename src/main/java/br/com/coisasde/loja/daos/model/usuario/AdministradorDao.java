package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.Administrador;

@Stateless
public class AdministradorDao extends Dao<Administrador> {

	public AdministradorDao() {
		super(Administrador.class);
	}
}
