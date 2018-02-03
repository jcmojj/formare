package br.com.clinicaformare.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.model.atendimento.Pacote;

@Stateless
public class PacoteDao extends Dao<Pacote> {

	public PacoteDao() {
		super(Pacote.class);
	}

	public List<Pacote> listaPacotesPadrao() {
		String jpql = "select p from Pacote p where p.ehPacotePadrao = true";
		TypedQuery<Pacote> query = super.getEntityManager().createQuery(jpql,Pacote.class);
		return query.getResultList();
	}
}