package br.com.clinicaformare.model.conversores.usuario;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.NivelProfissionalDao;
import br.com.clinicaformare.model.usuario.NivelProfissional;

@Named
public class NivelProfissionalConverter implements Converter{
	
	@Inject
	NivelProfissionalDao nivelProfissionalDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		NivelProfissional nivelProfissional = nivelProfissionalDao.buscaPorId(id);
		return nivelProfissional;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		NivelProfissional nivelProfissional = (NivelProfissional) object;
		if(nivelProfissional == null || nivelProfissional.getId() == null) {
			return null;
		}
		return String.valueOf(nivelProfissional.getId());
	}

}
