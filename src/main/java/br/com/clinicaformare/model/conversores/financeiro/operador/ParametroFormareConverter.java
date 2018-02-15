package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.ParametroFormareDao;
import br.com.clinicaformare.model.financeiro.operador.ParametroFormare;

@Named
public class ParametroFormareConverter implements Converter{
	
	@Inject
	ParametroFormareDao parametroFormareDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		ParametroFormare parametroFormare = parametroFormareDao.buscaPorId(id);
		return parametroFormare;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		ParametroFormare parametroFormare = (ParametroFormare) object;
		if(parametroFormare == null || parametroFormare.getId() == null) {
			return null;
		}
		return String.valueOf(parametroFormare.getId());
	}

}
