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
public class PuxaEspeciePeixeBean {
	
	@Transactional
	public void daInternet() throws IOException  {
		Set<String> peixes = new TreeSet<>();
		for (int i = 1; i < 48; i++) {
			System.out.println("PAGINA: " + i);
			char ichar = Character.forDigit(i, 10);
			Document doc = Jsoup
					.connect("https://www.rsdiscus.com.br/loja/catalogo.php?loja=102848&categoria=362&pg=" + ichar)
					.get();

			Elements especies = doc.select(".product-name h3");
			for (Element especieElement : especies) {
				String linha = especieElement.text();
				String especie = linha.substring(0, comprimento(linha));
				peixes.add(especie);
			}
		}
		for (String peixe : peixes) {
			System.out.println(peixe);
		}
		gravar(peixes,"Água Salgada");
		// ---
		peixes = new TreeSet<>();
		for (int i = 1; i < 4; i++) {
			System.out.println("PAGINA: " + i);
			char ichar = Character.forDigit(i, 10);
			Document doc = Jsoup
					.connect("https://www.rsdiscus.com.br/loja/catalogo.php?loja=102848&categoria=168&pg=" + ichar)
					.get();

			Elements especies = doc.select(".product-name h3");
			for (Element especieElement : especies) {
				String linha = especieElement.text();
				String especie = linha.substring(0, comprimento(linha));
				peixes.add(especie);
			}
		}
		for (String peixe : peixes) {
			System.out.println(peixe);
		}
		gravar(peixes,"Água Doce");
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
	
	private static void gravar(Set<String> peixes, String tipoAgua) throws IOException {
		BufferedWriter fr = new BufferedWriter(new FileWriter(
				"/Users/josecarlosoliveira/javaee/eclipse-workspace/coisasde/src/main/java/br/com/coisasde/loja/model/produto/novos/especiePeixe",true));
		for (String peixe : peixes) {
			fr.write(peixe);
			fr.write("; ");
			fr.write(tipoAgua);
			fr.newLine();
		}
		fr.flush();
		fr.close();

	}
}