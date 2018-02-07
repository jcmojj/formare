package br.com.clinicaformare.model.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.clinicaformare.daos.usuario.PacienteDao;
import br.com.clinicaformare.model.usuario.Paciente;

@FacesConverter("pacienteConverter")
public class PacienteConverter implements Converter{
	
	@Inject
	PacienteDao pacienteDao;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string == null || string.trim().isEmpty()) {
			return null;
		}
		Long id =Long.valueOf(string);
		Paciente paciente = pacienteDao.buscaPorId(id);
		return paciente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Paciente paciente = (Paciente) object;
		if(paciente == null || paciente.getId() == null) {
			return null;
		}
		return String.valueOf(paciente.getId());
	}

}
