package br.com.clinicaformare.bean.primefaces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ThemeSwitcherViewBean {
 
    private List<Theme> themes;
 
//    @ManagedProperty("#{themeService}")
    @Inject
    private ThemeService service;
 
    @PostConstruct
    public void init() {
    	
        themes = service.getThemes();
    }
 
    public List<Theme> getThemes() {
        return themes;
    }
 
    public void setService(ThemeService service) {
        this.service = service;
    }
}
