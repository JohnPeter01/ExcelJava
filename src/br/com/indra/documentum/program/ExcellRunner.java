package br.com.indra.documentum.program;

import br.com.indra.documentum.repository.DocumentumRepository;

public class ExcellRunner {

	public static void main(String[] args) {
		DocumentumRepository rep = new DocumentumRepository();
		System.out.println(rep.getSessDctm());
	}

}
