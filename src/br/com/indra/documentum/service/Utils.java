package br.com.indra.documentum.service;

import java.util.ArrayList;

import br.com.indra.documentum.entities.Protocolo;
import br.com.indra.documentum.entities.Workflow;
import br.com.indra.documentum.repository.DocumentumRepository;

public class Utils {

	DocumentumRepository rep = new DocumentumRepository();

	public void buscaHistorico() {
		try {

			Protocolo historico = rep.getHistorico("25495948");

			ArrayList<Workflow> workflows = rep.getWorkflow(historico
					.getIdWorkflow());


			for (Workflow workflow : workflows) {
				System.out.println(workflow.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delegarEmLote() {
		
	}
	
	public void excluirEmLote() {
		
	}
	
	public void historicoEmLote(String[] protocolos) {
		
		int i = 0;
		for(i = 0; i < protocolos.length; i++){
			System.out.println(protocolos[i]);
			try {
	
				Protocolo historico = rep.getHistoricoEmLote(protocolos[i]);
				
				
				ArrayList<Workflow> workflows = rep.getWorkflow(historico
						.getIdWorkflow());
				System.out.println(workflows.indexOf(0));
	
				for (Workflow workflow : workflows) {
					System.out.println("ENTREI AQUI");
					System.out.println(workflow.toString());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}
}
