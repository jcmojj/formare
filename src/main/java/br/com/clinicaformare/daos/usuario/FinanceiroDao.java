package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Financeiro;

@Stateless
public class FinanceiroDao extends Dao<Financeiro> {

	public FinanceiroDao() {
		super(Financeiro.class);
	}
}