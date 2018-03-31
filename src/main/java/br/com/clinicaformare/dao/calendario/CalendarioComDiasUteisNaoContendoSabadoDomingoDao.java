package br.com.clinicaformare.dao.calendario;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.calendario.CalendarioComDiasUteisNaoContendoSabadoDomingo;

@Stateless
@CalendarioQualifier(calendario = CalendarioDiasUteis.SEMSABADO_SEMDOMINGO)
public class CalendarioComDiasUteisNaoContendoSabadoDomingoDao extends CalendarioDao<CalendarioComDiasUteisNaoContendoSabadoDomingo> {
	
	public CalendarioComDiasUteisNaoContendoSabadoDomingoDao() {
		super(CalendarioComDiasUteisNaoContendoSabadoDomingo.class);
	}

}