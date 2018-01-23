package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;

@Stateless
public class PaganteDao extends Dao<PaganteDao> {

	public PaganteDao() {
		super(PaganteDao.class);
	}
}
