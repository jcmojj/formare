package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.clinicaformare.daos.usuario.ProfissionalDao;
import br.com.clinicaformare.model.usuario.Profissional;

@FacesConverter("profissionalConverter")
public class ProfissionalConverter implements Converter{
	
	@Inject
	ProfissionalDao profissionalDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Profissional profissional = profissionalDao.buscaPorId(id);
		return profissional;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Profissional profissional = (Profissional) object;
		if(profissional == null || profissional.getId() == null) {
			return null;
		}
		return String.valueOf(profissional.getId());
	}

}
