package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

import br.com.clinicaformare.usuario.endereco.Paesci;

@Stateless
public class PaesciDao extends Dao<Paesci> {

	public PaesciDao() {
		super(Paesci.class);
	}
}
