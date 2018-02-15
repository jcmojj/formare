package br.com.clinicaformare.model.conversores.financeiro.operador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.financeiro.operacao.BancoDao;
import br.com.clinicaformare.model.financeiro.operador.Banco;

@Named
public class BancoConverter implements Converter {

	@Inject
	BancoDao bancoDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(string);
		Banco banco = bancoDao.buscaPorId(id);
		return banco;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Banco banco = (Banco) object;
		if (banco == null || banco.getId() == null) {
			return null;
		}
		return String.valueOf(banco.getId());
	}

}