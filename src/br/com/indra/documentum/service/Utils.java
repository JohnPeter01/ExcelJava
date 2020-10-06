package br.com.indra.documentum.service;

import java.util.ArrayList;

import br.com.indra.documentum.repository.DocumentumRepository;

public class Utils {
	
	DocumentumRepository rep = new DocumentumRepository();
	
	public void buscaHistorico() {
		try {
			ArrayList<String[]> historicos = rep.getHistorico("25495948");
			for (String[] historico : historicos) {
				for (String string : historico) {
					System.out.println(string);
				}
				System.out.println("-------------------------------------");
				ArrayList<String[]> workflow = rep.getWorkflow(historico[3]);
				
				for (String[] strings : workflow) {
					for (String string : strings) {
						System.out.println(string);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
