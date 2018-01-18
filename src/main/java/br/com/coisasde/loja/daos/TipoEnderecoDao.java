package br.com.coisasde.loja.daos;

import javax.ejb.Stateless;

@Stateless
public class TipoEnderecoDao extends Dao<TipoEnderecoDao> {

	public TipoEnderecoDao() {
		super(TipoEnderecoDao.class);
	}
}
