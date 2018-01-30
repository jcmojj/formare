package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Stateless
public class TipoEnderecoDao extends Dao<TipoEndereco> {

	public TipoEnderecoDao() {
		super(TipoEndereco.class);
	}
}
