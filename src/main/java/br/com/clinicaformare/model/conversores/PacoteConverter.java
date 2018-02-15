package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.PacoteDao;
import br.com.clinicaformare.model.atendimento.Pacote;

@Named
public class PacoteConverter implements Converter{
	
	@Inject
	PacoteDao pacoteDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		Pacote pacote = pacoteDao.buscaPorId(id);
		return pacote;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Pacote pacote = (Pacote) object;
		if(pacote == null || pacote.getId() == null) {
			return null;
		}
		return String.valueOf(pacote.getId());
	}

}
