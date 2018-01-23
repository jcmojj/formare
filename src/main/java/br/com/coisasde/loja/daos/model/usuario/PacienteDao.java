package br.com.coisasde.loja.daos.model.usuario;

import javax.ejb.Stateless;

import br.com.coisasde.loja.daos.Dao;
import br.com.coisasde.loja.model.usuario.Paciente;

@Stateless
public class PacienteDao extends Dao<Paciente> {

	public PacienteDao() {
		super(Paciente.class);
	}
}
