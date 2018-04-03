package br.com.clinicaformare.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface UsuarioLogadoQualifier {

}


//@Qualifier
//@Retention(RetentionPolicy.RUNTIME)
//@Target({TYPE, METHOD, FIELD, PARAMETER})
//public @interface CalendarioQualifier {
//	CalendarioDiasUteis calendario() default 
//	CalendarioDiasUteis.SEMSABADO_SEMDOMINGO_SEMFERIADO;
//}