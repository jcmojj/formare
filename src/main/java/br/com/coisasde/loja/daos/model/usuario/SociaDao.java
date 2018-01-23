package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.Socia;

@Stateless
public class SociaDao extends Dao<Socia> {

	public SociaDao() {
		super(Socia.class);
	}
}
