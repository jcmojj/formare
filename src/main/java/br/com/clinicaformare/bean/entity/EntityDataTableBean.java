package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.model.acesso.TipoEntidade;

@Named
@ViewScoped
public class EntityDataTableBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject @Any
	Instance<EntityBean<?>> entityBeanIntance;
	
	
	private List<TipoEntidade> tiposEntidade;
	private List<TipoEntidade> tiposEntidadeSorted;
	private List<String> grupos;
	
	public EntityDataTableBean() {
		super();
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
	
	@SuppressWarnings("rawtypes")
	public String abrir(TipoEntidade tipoEntidade) {
		for (EntityBean eb : entityBeanIntance) {
			 System.out.println("abrir: " + eb.getClass().getSimpleName());
			 if(eb.getClass().getSimpleName().startsWith(tipoEntidade.getNomeBean())) {
				 return eb.abrir();
	        }
		}
		return "";
	}
	
	
	public void inicializando() {
		System.out.println("Inicializando com e sem nada");
		 entityBeanIntance.iterator().forEachRemaining(eb -> 
		 {
			System.out.println("Incializando: " + eb.getClass().getSimpleName());
	        	eb.inicializando();
		 });
	 }
	 
	 @SuppressWarnings("rawtypes")
	public String inicializando(TipoEntidade tipoEntidade) {
		 System.out.println("Inicializando com parâmetro");
		 for (EntityBean eb : entityBeanIntance) {
			 System.out.println("abrir: " + eb.getClass().getSimpleName());
			 if(eb.getClass().getSimpleName().startsWith(tipoEntidade.getNomeBean())) {
				 System.out.println("Escolhido: " + eb.getClass().getSimpleName());
				 return eb.inicializando();
	        }
		}
		return "";
		  }
	public String inicializa(TipoEntidade tipoEntidade) {
		 if (!entityBeanIntance.isUnsatisfied() && !entityBeanIntance.isAmbiguous()) {
			 entityBeanIntance.get().inicializando();
		    }else {
		    	 System.out.println("Não achou a instancia");
		    }
		 return "/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true";
	}
	
	 public static EntityBean<?> getCurrentInstance(TipoEntidade tipoEntidade) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         return (EntityBean<?>) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, tipoEntidade.getNomeBean());
     }
}
