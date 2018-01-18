package br.com.coisasde.loja.bean.inicializar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Named
@RequestScoped
public class PuxaRacaGatoBean {
	
	@Transactional
	public void daInternet() throws IOException  {
		Set<String> gatos = new TreeSet<>();
			Document doc = Jsoup	.connect("http://www.gatosmania.com/racas").get();

			Elements especies = doc.select(".TituloArticulo");
			for (Element especieElement : especies) {
				String linha = especieElement.text();
				String especie = linha.substring(0, comprimento(linha));
				gatos.add(especie);
			}
		for (String gato : gatos) {
			System.out.println(gato);
		}
		gravar(gatos);
		
	}

	private static Integer comprimento(String linha) {
		Integer comprimento = linha.length();
		for (int i = 0; i < 10; i++) {
			char ichar = Character.forDigit(i, 10);
			if (linha.indexOf(ichar) < comprimento && linha.indexOf(ichar) > 0) {
				comprimento = linha.indexOf(ichar);
			}
		}
		if (linha.indexOf("(") < comprimento && linha.indexOf("(") > 0) {
			comprimento = linha.indexOf("(");
		}
		if (linha.indexOf(")") < comprimento && linha.indexOf(")") > 0) {
			comprimento = linha.indexOf(")");
		}
		return comprimento;
	}
	
	private static void gravar(Set<String> gatos) throws IOException {
		BufferedWriter fr = new BufferedWriter(new FileWriter(
				"/Users/josecarlosoliveira/javaee/eclipse-workspace/coisasde/src/main/java/br/com/coisasde/loja/model/produto/novos/racaGato",true));
		for (String gato : gatos) {
			fr.write(gato);
			fr.newLine();
		}
		fr.flush();
		fr.close();

	}
}