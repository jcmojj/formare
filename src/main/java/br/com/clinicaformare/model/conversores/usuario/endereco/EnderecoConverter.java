package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.EnderecoDao;
import br.com.clinicaformare.usuario.endereco.Endereco;

@Named
public class EnderecoConverter implements Converter{
	
	@Inject
	EnderecoDao enderecoDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Endereco endereco = enderecoDao.buscaPorId(id);
		return endereco;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Endereco endereco = (Endereco) object;
		if(endereco == null || endereco.getId() == null) {
			return null;
		}
		return String.valueOf(endereco.getId());
	}

}
