package br.com.coisasde.loja.daos;

import javax.ejb.Stateless;

import br.com.coisasde.loja.model.Parametro;

@Stateless
public class ParametroDao extends Dao<Parametro> {

	public ParametroDao() {
		super(Parametro.class);
	}
}
