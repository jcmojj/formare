package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.TipoTelefoneDao;
import br.com.clinicaformare.usuario.endereco.TipoTelefone;

@Named
public class TipoTelefoneConverter implements Converter{
	
	@Inject
	TipoTelefoneDao tipoTelefoneDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		TipoTelefone tipoTelefone = tipoTelefoneDao.buscaPorId(id);
		return tipoTelefone;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		TipoTelefone tipoTelefone = (TipoTelefone) object;
		if(tipoTelefone == null || tipoTelefone.getId() == null) {
			return null;
		}
		return String.valueOf(tipoTelefone.getId());
	}

}
