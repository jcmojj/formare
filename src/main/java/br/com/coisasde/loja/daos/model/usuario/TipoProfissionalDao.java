package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.TipoProfissional;

@Stateless
public class TipoProfissionalDao extends Dao<TipoProfissional> {

	public TipoProfissionalDao() {
		super(TipoProfissional.class);
	}
}
