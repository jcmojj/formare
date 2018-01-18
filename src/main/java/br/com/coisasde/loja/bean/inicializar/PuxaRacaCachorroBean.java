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
public class PuxaRacaCachorroBean {
	
	@Transactional
	public void daInternet() throws IOException  {
		Set<String> cachorros = new TreeSet<>();
		Integer aAsNumber;
		Integer variableAsNumber;
		char ichar;
		for (int i = 1; i < 24; i++) {
			aAsNumber = (int)'a';
			variableAsNumber = aAsNumber + i - 1;
			ichar = Character.forDigit(variableAsNumber, 10);
			System.out.println("PAGINA: " + i);
			Document doc = Jsoup
					.connect("http://portaldodog.com.br/cachorros/racas-de-cachorros/" + ichar)
					.get();

			Elements especies = doc.select(".card-title");
			for (Element especieElement : especies) {
				String linha = especieElement.text();
				String especie = linha.substring(0, comprimento(linha));
				cachorros.add(especie);
			}
		}
		for (String cachorro : cachorros) {
			System.out.println(cachorro);
		}
		gravar(cachorros);
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
	
	private static void gravar(Set<String> cachorros) throws IOException {
		BufferedWriter fr = new BufferedWriter(new FileWriter(
				"/Users/josecarlosoliveira/javaee/eclipse-workspace/coisasde/src/main/java/br/com/coisasde/loja/model/produto/novos/racaCachorro",true));
		for (String cachorro : cachorros) {
			fr.write(cachorro);
			fr.newLine();
		}
		fr.flush();
		fr.close();

	}
}