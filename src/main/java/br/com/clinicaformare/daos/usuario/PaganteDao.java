package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;

@Stateless
public class PaganteDao extends Dao<PaganteDao> {

	public PaganteDao() {
		super(PaganteDao.class);
	}
}