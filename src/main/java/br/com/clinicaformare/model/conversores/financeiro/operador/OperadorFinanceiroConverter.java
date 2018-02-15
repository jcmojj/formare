package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.OperadorFinanceiroDao;
import br.com.clinicaformare.model.financeiro.operador.OperadorFinanceiro;

@Named
public class OperadorFinanceiroConverter implements Converter{
	
	@Inject
	OperadorFinanceiroDao operadorFinanceiroDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		OperadorFinanceiro operadorFinanceiro = operadorFinanceiroDao.buscaPorId(id);
		return operadorFinanceiro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		OperadorFinanceiro operadorFinanceiro = (OperadorFinanceiro) object;
		if(operadorFinanceiro == null || operadorFinanceiro.getId() == null) {
			return null;
		}
		return String.valueOf(operadorFinanceiro.getId());
	}

}
