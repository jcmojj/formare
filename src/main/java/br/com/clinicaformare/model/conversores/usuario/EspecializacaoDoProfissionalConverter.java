package br.com.clinicaformare.model.conversores.usuario;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.EspecializacaoDoProfissionalDao;
import br.com.clinicaformare.model.usuario.EspecializacaoDoProfissional;

@Named
public class EspecializacaoDoProfissionalConverter implements Converter{
	
	@Inject
	EspecializacaoDoProfissionalDao especializacaoDoProfissionalDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		EspecializacaoDoProfissional especializacaoDoProfissional = especializacaoDoProfissionalDao.buscaPorId(id);
		return especializacaoDoProfissional;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		EspecializacaoDoProfissional especializacaoDoProfissional = (EspecializacaoDoProfissional) object;
		if(especializacaoDoProfissional == null || especializacaoDoProfissional.getId() == null) {
			return null;
		}
		return String.valueOf(especializacaoDoProfissional.getId());
	}

}
