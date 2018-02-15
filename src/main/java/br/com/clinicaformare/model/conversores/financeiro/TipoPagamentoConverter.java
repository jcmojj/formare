package br.com.clinicaformare.model.conversores.financeiro;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.TipoPagamentoDao;
import br.com.clinicaformare.model.financeiro.TipoPagamento;

@Named
public class TipoPagamentoConverter implements Converter{
	
	@Inject
	TipoPagamentoDao tipoPagamentoDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		TipoPagamento tipoPagamento = tipoPagamentoDao.buscaPorId(id);
		return tipoPagamento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		TipoPagamento tipoPagamento = (TipoPagamento) object;
		if(tipoPagamento == null || tipoPagamento.getId() == null) {
			return null;
		}
		return String.valueOf(tipoPagamento.getId());
	}

}
