package br.com.clinicaformare.daos.usuario;


import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Secretaria;

@Stateless
public class SecretariaDao extends Dao<Secretaria> {

	public SecretariaDao() {
		super(Secretaria.class);
	}
}