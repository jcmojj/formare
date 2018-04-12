package br.com.clinicaformare.bean.primefaces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class ThemeSwitcherViewBean {

	private List<Theme> themes;
	private Theme tema;

	// @ManagedProperty("#{themeService}")
//	@Inject 
//	private ThemeService service;

	@PostConstruct
	public void init() {
		System.out.println("ThemeSwitcher - Post Construct!");
//		themes = service.getThemes();
		themes = new ArrayList<Theme>();
		themes.add(new Theme(0, "Afterdark", "afterdark"));
		themes.add(new Theme(1, "Afternoon", "afternoon"));
		themes.add(new Theme(2, "Afterwork", "afterwork"));
		themes.add(new Theme(3, "Aristo", "aristo"));
		themes.add(new Theme(4, "Black-Tie", "black-tie"));
		themes.add(new Theme(5, "Blitzer", "blitzer"));
		themes.add(new Theme(6, "Bluesky", "bluesky"));
		themes.add(new Theme(7, "Bootstrap", "bootstrap"));
		themes.add(new Theme(8, "Casablanca", "casablanca"));
		themes.add(new Theme(9, "Cupertino", "cupertino"));
		themes.add(new Theme(10, "Cruze", "cruze"));
		themes.add(new Theme(11, "Dark-Hive", "dark-hive"));
		themes.add(new Theme(12, "Delta", "delta"));
		themes.add(new Theme(13, "Dot-Luv", "dot-luv"));
		themes.add(new Theme(14, "Eggplant", "eggplant"));
		themes.add(new Theme(15, "Excite-Bike", "excite-bike"));
		themes.add(new Theme(16, "Flick", "flick"));
		themes.add(new Theme(17, "Glass-X", "glass-x"));
		themes.add(new Theme(18, "Home", "home"));
		themes.add(new Theme(19, "Hot-Sneaks", "hot-sneaks"));
		themes.add(new Theme(20, "Humanity", "humanity"));
		themes.add(new Theme(21, "Le-Frog", "le-frog"));
		themes.add(new Theme(22, "Midnight", "midnight"));
		themes.add(new Theme(23, "Mint-Choc", "mint-choc"));
		themes.add(new Theme(24, "Overcast", "overcast"));
		themes.add(new Theme(25, "Pepper-Grinder", "pepper-grinder"));
		themes.add(new Theme(26, "Redmond", "redmond"));
		themes.add(new Theme(27, "Rocket", "rocket"));
		themes.add(new Theme(28, "Sam", "sam"));
		themes.add(new Theme(29, "Smoothness", "smoothness"));
		themes.add(new Theme(30, "South-Street", "south-street"));
		themes.add(new Theme(31, "Start", "start"));
		themes.add(new Theme(32, "Sunny", "sunny"));
		themes.add(new Theme(33, "Swanky-Purse", "swanky-purse"));
		themes.add(new Theme(34, "Trontastic", "trontastic"));
		themes.add(new Theme(35, "UI-Darkness", "ui-darkness"));
		themes.add(new Theme(36, "UI-Lightness", "ui-lightness"));
		themes.add(new Theme(37, "Vader", "vader"));
//		themes.add(new Theme(38, "Nenhum", "none"));
		tema = themes.get(7); // ESCOLHENDO TEMA
	}

	public List<Theme> getThemes() {
		System.out.println("ThemeSwitcher - Get themes");
		return themes;
	}

//	public void setService(ThemeService service) {
//		this.service = service;
//	}
//
	public Theme getTema() {
		System.out.println("ThemeSwitcher - getTema");
		return tema;
	}

	public void setTema(Theme tema) {
		this.tema = tema;
	}


	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
	

	
}
