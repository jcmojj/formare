package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.NivelProfissional;

@Stateless
public class NivelProfissionalDao extends Dao<NivelProfissional> {

	public NivelProfissionalDao() {
		super(NivelProfissional.class);
	}
}