package br.com.clinicaformare.model.conversores.usuario;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.ResponsavelFinanceiroDao;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;

@Named
public class ResponsavelFinanceiroConverter implements Converter{
	
	@Inject
	ResponsavelFinanceiroDao responsavelFinanceiroDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		ResponsavelFinanceiro responsavelFinanceiro = responsavelFinanceiroDao.buscaPorId(id);
		return responsavelFinanceiro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object == null) return null;
		ResponsavelFinanceiro responsavelFinanceiro = (ResponsavelFinanceiro) object;
		if( responsavelFinanceiro == null || responsavelFinanceiro.getId() == null) {
			return null;
		}
		return String.valueOf(responsavelFinanceiro.getId());
	}

}
