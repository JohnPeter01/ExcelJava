package br.com.indra.documentum.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Utils {
	
	public ArrayList<String> lerArquivo(String nomeArquivo) {
		ArrayList<String> array = new ArrayList<String>();
		
		try {
			File arquivo = new File(nomeArquivo);
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			while (br.ready()) {

				String linha = br.readLine();
				
				array.add(linha);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return array;
	}

}
