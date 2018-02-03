package br.com.clinicaformare.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;

@Stateless
public class AtendimentoPadraoDao extends Dao<AtendimentoPadrao> {

	public AtendimentoPadraoDao() {
		super(AtendimentoPadrao.class);
	}

	public List<AtendimentoPadrao> listaAtendimentosPadraoDePacote(Long pacotePadraoId) {
		String jpql = "select a from AtendimentoPadrao a where a.pacote.id = :pacote";System.out.println("aqui1");
		TypedQuery<AtendimentoPadrao> query = super.getEntityManager().createQuery(jpql,AtendimentoPadrao.class);System.out.println("aqui2");
		query.setParameter("pacote", pacotePadraoId);System.out.println("aqui3");
		return query.getResultList();
	}
}