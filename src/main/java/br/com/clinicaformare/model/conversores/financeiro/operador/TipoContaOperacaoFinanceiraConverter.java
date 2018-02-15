package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.TipoContaOperacaoFinanceiraDao;
import br.com.clinicaformare.model.financeiro.operador.TipoContaOperacaoFinanceira;

@Named
public class TipoContaOperacaoFinanceiraConverter implements Converter{
	
	@Inject
	TipoContaOperacaoFinanceiraDao tipoContaOperacaoFinanceiraDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		TipoContaOperacaoFinanceira tipoContaOperacaoFinanceira = tipoContaOperacaoFinanceiraDao.buscaPorId(id);
		return tipoContaOperacaoFinanceira;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		TipoContaOperacaoFinanceira tipoContaOperacaoFinanceira = (TipoContaOperacaoFinanceira) object;
		if(tipoContaOperacaoFinanceira == null || tipoContaOperacaoFinanceira.getId() == null) {
			return null;
		}
		return String.valueOf(tipoContaOperacaoFinanceira.getId());
	}

}
