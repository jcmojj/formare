package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.ColetorTarifaOperacaoFinanceiraDao;
import br.com.clinicaformare.model.financeiro.operador.ColetorTarifaOperacaoFinanceira;

@Named
public class ColetorTarifaOperacaoFinanceiraConverter implements Converter{
	
	@Inject
	ColetorTarifaOperacaoFinanceiraDao coletorTarifaOperacaoFinanceiraDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		ColetorTarifaOperacaoFinanceira coletorTarifaOperacaoFinanceira = coletorTarifaOperacaoFinanceiraDao.buscaPorId(id);
		return coletorTarifaOperacaoFinanceira;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		ColetorTarifaOperacaoFinanceira coletorTarifaOperacaoFinanceira = (ColetorTarifaOperacaoFinanceira) object;
		if(coletorTarifaOperacaoFinanceira == null || coletorTarifaOperacaoFinanceira.getId() == null) {
			return null;
		}
		return String.valueOf(coletorTarifaOperacaoFinanceira.getId());
	}

}

