package br.com.clinicaformare.daos.financeiro.operacao;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.operador.Banco;

@Stateless
public class BancoDao extends Dao<Banco> {

	public BancoDao() {
		super(Banco.class);
	}
}
