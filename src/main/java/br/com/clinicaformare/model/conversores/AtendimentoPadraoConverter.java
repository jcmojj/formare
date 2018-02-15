package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.AtendimentoPadraoDao;
import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;

@Named
public class AtendimentoPadraoConverter implements Converter{
	
	@Inject
	AtendimentoPadraoDao atendimentoPadraoDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		AtendimentoPadrao atendimentoPadrao = atendimentoPadraoDao.buscaPorId(id);
		return atendimentoPadrao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		AtendimentoPadrao atendimentoPadrao = (AtendimentoPadrao) object;
		if(atendimentoPadrao == null || atendimentoPadrao.getId() == null) {
			return null;
		}
		return String.valueOf(atendimentoPadrao.getId());
	}

}
