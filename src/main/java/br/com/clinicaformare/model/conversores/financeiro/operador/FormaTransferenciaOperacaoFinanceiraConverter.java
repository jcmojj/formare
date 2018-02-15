package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.FormaTransferenciaOperacaoFinanceiraDao;
import br.com.clinicaformare.model.financeiro.operador.FormaTransferenciaOperacaoFinanceira;

@Named
public class FormaTransferenciaOperacaoFinanceiraConverter implements Converter{
	
	@Inject
	FormaTransferenciaOperacaoFinanceiraDao formaTransferenciaOperacaoFinanceiraDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		FormaTransferenciaOperacaoFinanceira formaTransferenciaOperacaoFinanceira = formaTransferenciaOperacaoFinanceiraDao.buscaPorId(id);
		return formaTransferenciaOperacaoFinanceira;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		FormaTransferenciaOperacaoFinanceira formaTransferenciaOperacaoFinanceira = (FormaTransferenciaOperacaoFinanceira) object;
		if(formaTransferenciaOperacaoFinanceira == null || formaTransferenciaOperacaoFinanceira.getId() == null) {
			return null;
		}
		return String.valueOf(formaTransferenciaOperacaoFinanceira.getId());
	}

}
