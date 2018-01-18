package br.com.coisasde.loja.daos;

import javax.ejb.Stateless;

import br.com.coisasde.loja.model.usuario.Autorizacao;

@Stateless
public class AutorizacaoDao extends Dao<Autorizacao> {

	public AutorizacaoDao() {
		super(Autorizacao.class);
	}	
}
