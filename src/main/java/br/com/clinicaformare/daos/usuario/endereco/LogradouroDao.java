package br.com.clinicaformare.daos.usuario.endereco;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Logradouro;

@Stateless
public class LogradouroDao extends Dao<Logradouro> {

	public LogradouroDao() {
		super(Logradouro.class);
	}
}
