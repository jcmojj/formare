package br.com.clinicaformare.dao.calendario;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.calendario.CalendarioComDiasUteisNaoContendoDomingoFeriado;

@Stateless
@CalendarioQualifier(calendario = CalendarioDiasUteis.SEMDOMINGO_SEMFERIADO)
public class CalendarioComDiasUteisNaoContendoDomingoFeriadoDao extends CalendarioDao<CalendarioComDiasUteisNaoContendoDomingoFeriado> {
	
	public CalendarioComDiasUteisNaoContendoDomingoFeriadoDao() {
		super(CalendarioComDiasUteisNaoContendoDomingoFeriado.class);
	}
}