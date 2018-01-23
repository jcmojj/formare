package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Administrador;

@Stateless
public class AdministradorDao extends Dao<Administrador> {

	public AdministradorDao() {
		super(Administrador.class);
	}
}