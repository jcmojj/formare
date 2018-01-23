package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.telefone.TipoTelefone;

@Stateless
public class TipoTelefoneDao extends Dao<TipoTelefone> {

	public TipoTelefoneDao() {
		super(TipoTelefone.class);
	}
	
}
