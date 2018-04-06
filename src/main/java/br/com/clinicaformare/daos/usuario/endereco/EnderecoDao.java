package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;
import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Endereco;

@Stateless
public class EnderecoDao extends Dao<Endereco> {

	public EnderecoDao() {
		super(Endereco.class);
	}
}