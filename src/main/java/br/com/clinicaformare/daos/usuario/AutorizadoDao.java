package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Autorizado;

@Stateless
public class AutorizadoDao extends Dao<Autorizado> {

	public AutorizadoDao() {
		super(Autorizado.class);
	}
}