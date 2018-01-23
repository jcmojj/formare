package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;

@Stateless
public class TipoEnderecoDao extends Dao<TipoEnderecoDao> {

	public TipoEnderecoDao() {
		super(TipoEnderecoDao.class);
	}
}
