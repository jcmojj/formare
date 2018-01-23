package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.TipoProfissional;

@Stateless
public class TipoProfissionalDao extends Dao<TipoProfissional> {

	public TipoProfissionalDao() {
		super(TipoProfissional.class);
	}
}