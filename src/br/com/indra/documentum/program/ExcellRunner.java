package br.com.indra.documentum.program;

import br.com.indra.documentum.service.Utils;


public class ExcellRunner {
	public static void main(String[] args) {
		Utils util = new Utils();
		//util.buscaHistorico();
		String[] protocolos = new String[] {"25495948", "0000069f801d850f"};
	
		util.historicoEmLote(protocolos);
	}

}
