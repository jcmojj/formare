package br.com.coisasde.loja.daos;

import javax.ejb.Stateless;

import br.com.coisasde.loja.model.usuario.telefone.TipoTelefone;

@Stateless
public class TipoTelefoneDao extends Dao<TipoTelefone> {

	public TipoTelefoneDao() {
		super(TipoTelefone.class);
	}
	
//	public TipoTelefone busca(String tipo) {
//		EntityManager manager1 = getEntityManager();
//		return manager1.find(TipoTelefone.class, tipo);
//	}
}
