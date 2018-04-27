package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.TelefoneDao;
import br.com.clinicaformare.usuario.endereco.Telefone;

@Named
public class TelefoneConverter implements Converter{
	
	@Inject
	TelefoneDao telefoneDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		
		try {
			Long id = Long.parseLong(string,10);
			System.out.println("Long Id: " + id);
			Telefone telefone = telefoneDao.buscaPorId(id);
			System.out.println("telefone"+telefone);
			return telefone;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
//		return (Telefone) component.getAttributes().get(string);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		System.out.println("TelefoneConverter" + " object: " + object);
		if(object == null) return null;
		Telefone telefone = (Telefone) object;
		if(telefone == null || telefone.getId() == null) {
			System.out.println("Null2");
			return null;
		}
		System.out.println("String.valueOf(telefone.getId())" + String.valueOf(telefone.getId()));
		return String.valueOf(telefone.getId());

//		component.getAttributes().put( telefone.getId().toString(), telefone);
//        return telefone.getId().toString();
	}

}
