package br.com.clinicaformare.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.model.atendimento.Atendimento;
import br.com.clinicaformare.model.atendimento.Pacote;

@Stateless
public class AtendimentoDao extends Dao<Atendimento> {

	public AtendimentoDao() {
		super(Atendimento.class);
	}

	public List<Atendimento> listaAtendimentosDePacote(Pacote pacote) {
		String jpql = "select a from Atendimento a where a.pacote.id = :pacote";System.out.println("aqui1");
		TypedQuery<Atendimento> query = super.getEntityManager().createQuery(jpql,Atendimento.class);System.out.println("aqui2");
		query.setParameter("pacote", pacote.getId());System.out.println("aqui3");
		return query.getResultList();
	}
}