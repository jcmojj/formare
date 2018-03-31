package br.com.clinicaformare.dao.calendario;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface CalendarioQualifier {
	CalendarioDiasUteis calendario() default 
	CalendarioDiasUteis.SEMSABADO_SEMDOMINGO_SEMFERIADO;
}
