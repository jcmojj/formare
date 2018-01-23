package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.Autorizado;

@Stateless
public class AutorizadoDao extends Dao<Autorizado> {

	public AutorizadoDao() {
		super(Autorizado.class);
	}
}
