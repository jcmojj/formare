package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.TarifaOperacaoFinanceiraDao;
import br.com.clinicaformare.model.financeiro.operador.TarifaOperacaoFinanceira;

@Named
public class TarifaOperacaoFinanceiraConverter implements Converter {

	@Inject
	TarifaOperacaoFinanceiraDao tarifaOperacaoFinanceiraDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		TarifaOperacaoFinanceira tarifaOperacaoFinanceira = tarifaOperacaoFinanceiraDao.buscaPorId(id);
		return tarifaOperacaoFinanceira;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		TarifaOperacaoFinanceira tarifaOperacaoFinanceira = (TarifaOperacaoFinanceira) object;
		if (tarifaOperacaoFinanceira == null || tarifaOperacaoFinanceira.getId() == null) {
			return null;
		}
		return String.valueOf(tarifaOperacaoFinanceira.getId());
	}

}
