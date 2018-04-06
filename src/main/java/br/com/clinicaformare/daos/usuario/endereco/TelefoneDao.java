package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Telefone;

@Stateless
public class TelefoneDao extends Dao<Telefone> {

	public TelefoneDao() {
		super(Telefone.class);
	}
}
