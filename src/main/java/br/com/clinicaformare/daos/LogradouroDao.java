package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

import br.com.clinicaformare.usuario.endereco.Logradouro;

@Stateless
public class LogradouroDao extends Dao<Logradouro> {

	public LogradouroDao() {
		super(Logradouro.class);
	}
}
