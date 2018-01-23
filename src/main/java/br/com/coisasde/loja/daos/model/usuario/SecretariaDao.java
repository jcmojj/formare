package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.Secretaria;

@Stateless
public class SecretariaDao extends Dao<Secretaria> {

	public SecretariaDao() {
		super(Secretaria.class);
	}
}
