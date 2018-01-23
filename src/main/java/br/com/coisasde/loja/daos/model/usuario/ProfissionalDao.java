package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.Profissional;

@Stateless
public class ProfissionalDao extends Dao<Profissional> {

	public ProfissionalDao() {
		super(Profissional.class);
	}
}
