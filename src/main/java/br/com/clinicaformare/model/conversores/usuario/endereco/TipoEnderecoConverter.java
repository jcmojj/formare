package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.TipoEnderecoDao;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Named
public class TipoEnderecoConverter implements Converter{
	
	@Inject
	TipoEnderecoDao tipoEnderecoDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		try {
			Long id =Long.valueOf(string);
			TipoEndereco tipoEndereco = tipoEnderecoDao.buscaPorId(id);
			return tipoEndereco;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		TipoEndereco tipoEndereco = (TipoEndereco) object;
		if(tipoEndereco == null || tipoEndereco.getId() == null) {
			return null;
		}
		return String.valueOf(tipoEndereco.getId());
	}

}
