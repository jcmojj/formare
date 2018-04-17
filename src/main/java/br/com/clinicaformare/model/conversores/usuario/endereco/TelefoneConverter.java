package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.TelefoneDao;
import br.com.clinicaformare.usuario.endereco.Acesso;

@Named
public class TelefoneConverter implements Converter{
	
	@Inject
	TelefoneDao telefoneDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Acesso telefone = telefoneDao.buscaPorId(id);
		return telefone;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Acesso telefone = (Acesso) object;
		if(telefone == null || telefone.getId() == null) {
			return null;
		}
		return String.valueOf(telefone.getId());
	}

}
