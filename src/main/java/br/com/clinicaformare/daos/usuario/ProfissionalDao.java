package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Profissional;

@Stateless
public class ProfissionalDao extends Dao<Profissional> {

	public ProfissionalDao() {
		super(Profissional.class);
	}
}