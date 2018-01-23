package br.com.clinicaformare.daos.usuario;

import javax.ejb.Stateless;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Paciente;

@Stateless
public class PacienteDao extends Dao<Paciente> {

	public PacienteDao() {
		super(Paciente.class);
	}
}