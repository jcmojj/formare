package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.PaesciDao;
import br.com.clinicaformare.usuario.endereco.Paesci;

@Named
public class PaesciConverter implements Converter{
	
	@Inject
	PaesciDao paesciDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Paesci paesci = paesciDao.buscaPorId(id);
		return paesci;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Paesci paesci = (Paesci) object;
		if(paesci == null || paesci.getId() == null) {
			return null;
		}
		return String.valueOf(paesci.getId());
	}

}
