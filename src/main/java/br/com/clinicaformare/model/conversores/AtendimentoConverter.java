package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.AtendimentoDao;
import br.com.clinicaformare.model.atendimento.Atendimento;

@Named
public class AtendimentoConverter implements Converter{
	
	@Inject
	AtendimentoDao atendimentoDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Atendimento atendimento = atendimentoDao.buscaPorId(id);
		return atendimento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Atendimento atendimento = (Atendimento) object;
		if(atendimento == null || atendimento.getId() == null) {
			return null;
		}
		return String.valueOf(atendimento.getId());
	}

}
