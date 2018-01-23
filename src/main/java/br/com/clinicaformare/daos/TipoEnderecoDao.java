package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

@Stateless
public class TipoEnderecoDao extends Dao<TipoEnderecoDao> {

	public TipoEnderecoDao() {
		super(TipoEnderecoDao.class);
	}
}
