package br.com.clinicaformare.listeners;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import br.com.clinicaformare.daos.financeiro.operacao.OperadorFinanceiroDao;
import br.com.clinicaformare.model.financeiro.operador.OperadorFinanceiro;

public class OperadorFinanceiroListener {

	@Inject
	OperadorFinanceiroDao operadorFinanceiroDao;

	private static OperadorFinanceiro objectWithTrue = null;

	public void init() { // call this method when application is started
		List<OperadorFinanceiro> operadoresFinanceiros = operadorFinanceiroDao.listaTodos();
		for (OperadorFinanceiro operador : operadoresFinanceiros) {
			if (operador.isOperadorFinanceiroPadrao()) {
				objectWithTrue = operador;
			}
		}
	}

	private void changeDefaultOperadorFinanceiro(OperadorFinanceiro changed) {
		if (changed.isOperadorFinanceiroPadrao()) {
			if (changed != objectWithTrue && objectWithTrue != null) {
				objectWithTrue.setOperadorFinanceiroPadrao(false);
			}
			objectWithTrue = changed;
		}
	}

	@PostPersist
	public void newOperadorFinanceiro(OperadorFinanceiro changed) {
		changeDefaultOperadorFinanceiro(changed);
	}

	@PostUpdate
	public void updateOperadorFinanceiro(OperadorFinanceiro changed) {
		changeDefaultOperadorFinanceiro(changed);
	}

	@PreRemove
	public void removeOperadorFinanceiro(OperadorFinanceiro changed) {
		if (changed.isOperadorFinanceiroPadrao() && objectWithTrue == changed) {
			objectWithTrue = null;
		}
	}
}
