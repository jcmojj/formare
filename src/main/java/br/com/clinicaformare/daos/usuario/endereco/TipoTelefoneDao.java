package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.TipoTelefone;

@Stateless
public class TipoTelefoneDao extends Dao<TipoTelefone> {

	public TipoTelefoneDao() {
		super(TipoTelefone.class);
	}
	
}
