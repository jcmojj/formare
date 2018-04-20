package br.com.clinicaformare.daos.usuario;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.Criador;

@Stateless
public class CriadorDao extends Dao<Criador> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager manager;

	public CriadorDao() {
		super(Criador.class);
	}
	
}