package br.com.clinicaformare.model.conversores.usuario;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.AdministradorDao;
import br.com.clinicaformare.model.usuario.Administrador;

@Named
public class AdministradorConverter implements Converter{
	
	@Inject
	AdministradorDao administradorDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Administrador administrador = administradorDao.buscaPorId(id);
		return administrador;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Administrador administrador = (Administrador) object;
		if(administrador == null || administrador.getId() == null) {
			return null;
		}
		return String.valueOf(administrador.getId());
	}

}
