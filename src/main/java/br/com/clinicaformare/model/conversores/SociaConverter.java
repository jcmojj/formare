package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.clinicaformare.daos.usuario.SociaDao;
import br.com.clinicaformare.model.usuario.Socia;

@FacesConverter("sociaConverter")
public class SociaConverter implements Converter{
	
	@Inject
	SociaDao sociaDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Socia socia = sociaDao.buscaPorId(id);
		return socia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Socia socia = (Socia) object;
		if(socia == null || socia.getId() == null) {
			return null;
		}
		return String.valueOf(socia.getId());
	}

}
