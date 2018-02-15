package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.usuario.Usuario;

@Named
public class UsuarioConverter implements Converter{
	
	@Inject
	UsuarioDao usuarioDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Usuario usuario = usuarioDao.buscaPorId(id);
		return usuario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Usuario usuario = (Usuario) object;
		if(usuario == null || usuario.getId() == null) {
			return null;
		}
		return String.valueOf(usuario.getId());
	}

}
