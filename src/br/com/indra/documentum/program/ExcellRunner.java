package br.com.indra.documentum.program;

import java.util.ArrayList;

import br.com.indra.documentum.service.Utils;


public class ExcellRunner {
	public static void main(String[] args) {
		Utils util = new Utils();
		//util.buscaHistorico();
		ArrayList<String> protocolos = new ArrayList();
		protocolos.add("25495948");
		protocolos.add("0000069f801d850f");
		util.historicoEmLote(protocolos);
	}

}
