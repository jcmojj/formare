package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.model.acesso.TipoEntidade;

@Named
@ViewScoped
public class EntityDataTableBean implements Serializable{
	private static final long serialVersionUID = 1L;
//	private List<String> entidadesToString = new ArrayList<>();
//	private List<String> entidadesMaiuscula = new ArrayList<>();
//	private TipoEntidade tipoEntidade;
	
	@Inject @Any
	Instance<EntityBean<?>> entityBeanIntance;
	
	
	private List<TipoEntidade> tiposEntidade;
	private List<TipoEntidade> tiposEntidadeSorted;
	private List<String> grupos;
	
	public EntityDataTableBean() {
		super();
//		for(TipoEntidade tipoEntidade:EnumSet.allOf(TipoEntidade.class))
//		EnumSet.allOf(TipoEntidade.class).stream().map(en -> entidades.add(en.toString()));
//		System.out.println("EntityDataTableBean");
//		(EnumSet.allOf(TipoEntidade.class)).stream().forEach(en -> System.out.println(en));
//		System.out.println("BBB");
//		entidadesToString = EnumSet.allOf(TipoEntidade.class).stream().map(Enum::toString).collect(Collectors.toList());
//		entidadesToString.stream().forEach(e->System.out.println(e));
//		entidadesMaiuscula = EnumSet.allOf(TipoEntidade.class).stream().map(Enum::name).collect(Collectors.toList());
//		entidadesMaiuscula.stream().forEach(e->System.out.println(e));
		tiposEntidade = EnumSet.allOf(TipoEntidade.class).stream().collect(Collectors.toList());
		tiposEntidadeSorted = EnumSet.allOf(TipoEntidade.class).stream().sorted(Comparator.comparing(TipoEntidade::getGrupo)).collect(Collectors.toList());
		grupos = EnumSet.allOf(TipoEntidade.class).stream().map(TipoEntidade::getGrupo).distinct().sorted().collect(Collectors.toList());

	}

	public List<TipoEntidade> getTiposEntidadeSorted() {
		return tiposEntidadeSorted;
	}

	public List<TipoEntidade> getTiposEntidade() {
		return tiposEntidade;
	}
	
	public List<TipoEntidade> getTiposEntidadedDe(String grupo){
		return EnumSet.allOf(TipoEntidade.class)
				.stream().filter(u->u.getGrupo().equals(grupo))
				.sorted(Comparator.comparing(TipoEntidade::getNomeBean))
				.collect(Collectors.toList());
	}
	
	public List<String> getGrupos(){
		return grupos;
	}
	
//	public void abre(String nomeBean) {
////		injetar o fooBean a partir de nomeBean
////		fooBean(nomeBean).abrir;
//	}
	public String abrir(TipoEntidade tipoEntidade) {
		listarInstancias();
		listarInstancias2(tipoEntidade);
//		if (isListar()) {
		System.out.println("abrir");
//		System.out.println("/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true");
//		System.out.println(getCurrentInstance(tipoEntidade).getClasse().getSimpleName());
//		System.out.println(getCurrentInstance(tipoEntidade).inicializando());
		System.out.println("teste");
			return "/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true";
//		} else {
//			return "";
//		}
	}
	
	 public void listarInstancias() {
		 System.out.println("listarInstancias");
		    for (EntityBean eb : entityBeanIntance) {
		        System.out.println(eb.toString());
		    }
		    System.out.println("abrir2");
		    for (EntityBean eb : entityBeanIntance) {
		        System.out.println(eb.getClasse().toString());
		    }
		    System.out.println("abrir3");
		    for (EntityBean eb : entityBeanIntance) {
		        System.out.println(eb.getClass().toString());
		    }
		    System.out.println("abrir4");
		    for (EntityBean eb : entityBeanIntance) {
		        System.out.println(eb.getClass().getSimpleName());
		    }
		    System.out.println("abrir5");
		    for (EntityBean eb : entityBeanIntance) {
		        System.out.println(eb.getClass().getName());
		    }
		  }
	 
	 public void listarInstancias2(TipoEntidade tipoEntidade) {
		 System.out.println("listarInstancias2");
		    for (EntityBean eb : entityBeanIntance) {
		        System.out.println(eb.toString());
		        Class<? extends EntityBean> class1 = eb.getClass();
		        Class class2 = eb.getClasse();
		        System.out.println("getClass:" + class1.getSimpleName());
		        System.out.println("Incializando");
		        if(eb.getClass().getSimpleName().startsWith(tipoEntidade.getNomeBean())) {
		        		eb.inicializando();
		        }
		        
		    }
//		    System.out.println("abrir4");
//		    for (EntityBean eb : entityBeanIntance) {
//		        System.out.println(eb.get\);
//		    }
		  }
//	
	public String inicializa(TipoEntidade tipoEntidade) {
		 if (!entityBeanIntance.isUnsatisfied() && !entityBeanIntance.isAmbiguous()) {
			 entityBeanIntance.get().inicializando();
		    }else {
		    	 System.out.println("Não achou a instancia");
		    }
		 return "/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true";
//		Class<?> clazz = Class.forName(nomeBean);
//		AnnotationLiteral<TipoEnderecoBean> annotationLiteral = new AnnotationLiteral<TipoEnderecoBean>() {
//			private static final long serialVersionUID = 1L;};
//		Instance<EntityBean<?>> instancia = entityBeanIntance.select(annotationLiteral.get());
//		instancia.in
//		fooBean(nomeBean).inicializando;
	}
	
	 public static EntityBean<?> getCurrentInstance(TipoEntidade tipoEntidade) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
//         Class<?> clazz = Class.forName(tipoEntidade.getNomeBean());
         return (EntityBean<?>) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, tipoEntidade.getNomeBean());
     }
}
