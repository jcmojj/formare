package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.Parametro;

@Stateless
public class ParametroDao extends Dao<Parametro> {

	public ParametroDao() {
		super(Parametro.class);
	}
}