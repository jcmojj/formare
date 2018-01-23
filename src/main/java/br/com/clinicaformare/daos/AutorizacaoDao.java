package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.usuario.Autorizacao;

@Stateless
public class AutorizacaoDao extends Dao<Autorizacao> {

	public AutorizacaoDao() {
		super(Autorizacao.class);
	}	
}
