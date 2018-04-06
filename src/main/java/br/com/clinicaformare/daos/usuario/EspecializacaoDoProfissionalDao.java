package br.com.clinicaformare.daos.usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.usuario.EspecializacaoDoProfissional;
import br.com.clinicaformare.model.usuario.NivelProfissional;

@Stateless
public class EspecializacaoDoProfissionalDao extends Dao<EspecializacaoDoProfissional> {

	public EspecializacaoDoProfissionalDao() {
		super(EspecializacaoDoProfissional.class);
	}

	public EspecializacaoDoProfissional buscaPorEspecializacaoENivel(String especializacao, NivelProfissional nivel) {
		String jpql = "select t from EspecializacaoDoProfissional t where t.especializacao = :especializacao and t.nivelProfissional = :nivel";
		TypedQuery<EspecializacaoDoProfissional> query = super.getEntityManager().createQuery(jpql,EspecializacaoDoProfissional.class);
		query.setParameter("especializacao", especializacao);
		query.setParameter("nivel", nivel);
		return query.getSingleResult();
	
	}
//	public List<String> listaStringDeTiposProfissionalDeSocia() {
//		String jpql = "select tipoProfissional.tipo from TipoProfissional tipoProfissional" + " where tipoProfissional.deSocia is true ";
//		TypedQuery<String> query = super.getEntityManager().createQuery(jpql, String.class);
//		return query.getResultList();
//	}

	public List<String> listaStringDeEspecializacaoDoProfissionalExistentes() {
		String jpql = "select ep.especializacao from EspecializacaoDoProfissional ep" + " join ep.profissionais profissional ";
//				+ " where tipoProfissional.deSocia is false ";
		TypedQuery<String> query = super.getEntityManager().createQuery(jpql, String.class);
		Set<String> set = new HashSet<>(query.getResultList());
		List<String> list = new ArrayList<>(set);
		return list;
	}
}