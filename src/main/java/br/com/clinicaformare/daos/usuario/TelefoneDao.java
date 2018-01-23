package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.telefone.Telefone;

@Stateless
public class TelefoneDao extends Dao<Telefone> {

	public TelefoneDao() {
		super(Telefone.class);
	}
}
