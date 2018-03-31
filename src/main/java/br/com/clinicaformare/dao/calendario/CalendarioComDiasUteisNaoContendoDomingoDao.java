package br.com.clinicaformare.dao.calendario;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.calendario.CalendarioComDiasUteisNaoContendoDomingo;

@Stateless
@CalendarioQualifier(calendario = CalendarioDiasUteis.SEMDOMINGO)
public class CalendarioComDiasUteisNaoContendoDomingoDao extends CalendarioDao<CalendarioComDiasUteisNaoContendoDomingo> {
	
	
	public CalendarioComDiasUteisNaoContendoDomingoDao() {
		super(CalendarioComDiasUteisNaoContendoDomingo.class);
	}

	
}