package br.com.clinicaformare.util.logger;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Specializes;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
//@Inherited
@Specializes // classe herdada, herda o qualificador
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD})
public @interface Auditavel {

}
