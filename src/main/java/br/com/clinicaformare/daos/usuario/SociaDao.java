package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Socia;

@Stateless
public class SociaDao extends Dao<Socia> {

	public SociaDao() {
		super(Socia.class);
	}
}