package br.com.clinicaformare.model.conversores.usuario.endereco;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.CodigoInternacionalTelefonicoDao;
import br.com.clinicaformare.usuario.endereco.CodigoInternacionalTelefonico;

@Named
public class CodigoInternacionalTelefonicoConverter implements Converter{
	
	@Inject
	CodigoInternacionalTelefonicoDao codigoInternacionalTelefonicoDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		try {
			Long id = Long.parseLong(string,10);
			CodigoInternacionalTelefonico codigoInternacionalTelefonico = codigoInternacionalTelefonicoDao.buscaPorId(id);
			return codigoInternacionalTelefonico;
		} catch (NumberFormatException e) {
//			context.addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                            "Selecione uma opção",
//                            "Selecione uma das opções"));
			e.printStackTrace();
		}
		return null;
//		return (CodigoInternacionalTelefonico) component.getAttributes().get(string);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		System.out.println("CodigoInternacionalTelefonicoConverter" + " object: " + object);
		if(object == null) return null;
		CodigoInternacionalTelefonico codigoInternacionalTelefonico = (CodigoInternacionalTelefonico) object;
		if(codigoInternacionalTelefonico == null || codigoInternacionalTelefonico.getId() == null) {
			System.out.println("Null2");
			return null;
		}
		System.out.println("String.valueOf(codigoInternacionalTelefonico.getId())" + String.valueOf(codigoInternacionalTelefonico.getId()));
		return String.valueOf(codigoInternacionalTelefonico.getId());
//		component.getAttributes().put( codigoInternacionalTelefonico.getId().toString(), codigoInternacionalTelefonico);
//        return codigoInternacionalTelefonico.getId().toString();
	}

}
