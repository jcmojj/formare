package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.CodigoInternacionalTelefonico;

@Stateless
public class CodigoInternacionalTelefonicoDao extends Dao<CodigoInternacionalTelefonico> {

	public CodigoInternacionalTelefonicoDao() {
		super(CodigoInternacionalTelefonico.class);
	}
}