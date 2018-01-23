package br.com.clinicaformare.daos;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.usuario.telefone.Telefone;

@Stateless
public class TelefoneDao extends Dao<Telefone> {

	public TelefoneDao() {
		super(Telefone.class);
	}
	
//	public Telefone busca(String tipo) {
//		EntityManager manager1 = getEntityManager();
//		return manager1.find(Telefone.class, tipo);
//	}
}
