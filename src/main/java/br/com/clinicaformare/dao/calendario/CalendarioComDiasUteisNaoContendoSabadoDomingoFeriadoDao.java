package br.com.clinicaformare.dao.calendario;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.calendario.CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado;

@Stateless
@CalendarioQualifier(calendario = CalendarioDiasUteis.SEMSABADO_SEMDOMINGO_SEMFERIADO)
public class CalendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao extends CalendarioDao<CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado> {

	public CalendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao() {
		super(CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado.class);
	}

}