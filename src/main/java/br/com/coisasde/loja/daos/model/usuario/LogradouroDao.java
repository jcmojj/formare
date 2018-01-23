package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.endereco.Logradouro;

@Stateless
public class LogradouroDao extends Dao<Logradouro> {

	public LogradouroDao() {
		super(Logradouro.class);
	}
}
