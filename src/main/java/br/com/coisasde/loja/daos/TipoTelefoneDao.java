package br.com.coisasde.loja.daos;

import javax.ejb.Stateless;

import br.com.coisasde.loja.model.usuario.telefone.TipoTelefone;

@Stateless
public class TipoTelefoneDao extends Dao<TipoTelefone> {

	public TipoTelefoneDao() {
		super(TipoTelefone.class);
	}
	
}
