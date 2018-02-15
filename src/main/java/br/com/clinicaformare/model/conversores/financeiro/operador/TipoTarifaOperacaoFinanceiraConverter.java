package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.TipoTarifaOperacaoFinanceiraDao;
import br.com.clinicaformare.model.financeiro.operador.TipoTarifaOperacaoFinanceira;

@Named
public class TipoTarifaOperacaoFinanceiraConverter implements Converter{
	
	@Inject
	TipoTarifaOperacaoFinanceiraDao tipoTarifaOperacaoFinanceiraDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		TipoTarifaOperacaoFinanceira tipoTarifaOperacaoFinanceira = tipoTarifaOperacaoFinanceiraDao.buscaPorId(id);
		return tipoTarifaOperacaoFinanceira;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		TipoTarifaOperacaoFinanceira tipoTarifaOperacaoFinanceira = (TipoTarifaOperacaoFinanceira) object;
		if(tipoTarifaOperacaoFinanceira == null || tipoTarifaOperacaoFinanceira.getId() == null) {
			return null;
		}
		return String.valueOf(tipoTarifaOperacaoFinanceira.getId());
	}

}


