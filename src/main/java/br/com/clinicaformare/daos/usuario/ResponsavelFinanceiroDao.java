package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;

@Stateless
public class ResponsavelFinanceiroDao extends Dao<ResponsavelFinanceiro> {

	public ResponsavelFinanceiroDao() {
		super(ResponsavelFinanceiro.class);
	}
}