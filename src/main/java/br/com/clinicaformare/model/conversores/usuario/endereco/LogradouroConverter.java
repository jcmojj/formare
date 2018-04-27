package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.usuario.endereco.Logradouro;

@Named
public class LogradouroConverter implements Converter{
	
//	@Inject @Entidade(tipo = TipoEntidade.LOGRADOURO)
	@Inject
	LogradouroDao logradouroDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		try {
			Long id =Long.valueOf(string);
			Logradouro logradouro = logradouroDao.buscaPorId(id);
			return logradouro;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		Logradouro logradouro = (Logradouro) object;
		if(logradouro == null || logradouro.getId() == null) {
			return null;
		}
		return String.valueOf(logradouro.getId());
	}

}
