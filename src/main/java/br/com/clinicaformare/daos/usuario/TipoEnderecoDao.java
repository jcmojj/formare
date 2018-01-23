package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;

@Stateless
public class TipoEnderecoDao extends Dao<TipoEnderecoDao> {

	public TipoEnderecoDao() {
		super(TipoEnderecoDao.class);
	}
}
