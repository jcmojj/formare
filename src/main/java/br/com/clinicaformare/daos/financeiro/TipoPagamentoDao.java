package br.com.clinicaformare.daos.financeiro;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.financeiro.TipoPagamento;

@Stateless
public class TipoPagamentoDao extends Dao<TipoPagamento> {

	public TipoPagamentoDao() {
		super(TipoPagamento.class);
	}	
}