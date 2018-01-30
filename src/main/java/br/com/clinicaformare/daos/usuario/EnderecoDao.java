package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;
import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Endereco;

@Stateless
public class EnderecoDao extends Dao<Endereco> {

	public EnderecoDao() {
		super(Endereco.class);
	}
}