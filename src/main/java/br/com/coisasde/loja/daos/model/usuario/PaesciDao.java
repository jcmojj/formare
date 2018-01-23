package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.endereco.Paesci;

@Stateless
public class PaesciDao extends Dao<Paesci> {

	public PaesciDao() {
		super(Paesci.class);
	}
}
