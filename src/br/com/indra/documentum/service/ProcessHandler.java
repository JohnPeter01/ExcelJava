package br.com.indra.documentum.service;

import java.util.ArrayList;

import br.com.indra.documentum.entities.Atividade;
import br.com.indra.documentum.entities.Protocolo;
import br.com.indra.documentum.entities.Workflow;
import br.com.indra.documentum.repository.DocumentumRepository;

public class ProcessHandler {

	DocumentumRepository rep = new DocumentumRepository();

	public void buscaHistorico() {
		try {

			Protocolo historico = rep.getHistorico("25495948");

			ArrayList<Workflow> workflows = rep.getWorkflow(historico
					.getIdWorkflow());

			System.out.println("----------------------------------------------------------------------------------------------");
		    System.out.printf("%10s %20s %15s %20s %20s", "*OBJECT ID*", "*RESPONSAVEL*", "*ATIVIDADE*", "*DT INICIO*", "*DT FIM*");
		    System.out.println();
		    System.out.println("----------------------------------------------------------------------------------------------");
		    for (Workflow workflow : workflows) {
		        System.out.format("%10s %10s %17ss %25s %15s",
		        		workflow.getRObjectId(), 
		        		workflow.getNomeResponsavel(), 
		        		workflow.getNomeAtividade(), 
		        		workflow.getDataInicio(), 
		        		workflow.getDataTermino()
		        		);
		        System.out.println();
		    }
		    System.out.println("----------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delegarEmLote(ArrayList<String> protocolos) {
		
		try {
			
			protocolos.add("25495948");

			ArrayList<Atividade> atividades = new ArrayList<Atividade>();
			
			for (String codProtocolo : protocolos) {
				
			
			Protocolo prtocolo = rep.getHistorico(codProtocolo);
			
			atividades.add(rep.getAtividade(prtocolo.getIdWorkflow()));
			
			}

			System.out.println("----------------------------------------------------------------------------------------------");
		    System.out.printf("%10s %15s %25s", "*ID_WORKFLOW*", "*ITEM_ID*", "*ATUAL_RESPONSÁVEL*");
		    System.out.println();
		    System.out.println("----------------------------------------------------------------------------------------------");
		    for (Atividade atividade : atividades) {
		        System.out.format("%10s %15s %15s",
		        		atividade.getIdWorkflow(), 
		        		atividade.getRObjectId(),
		        		atividade.getRPerformerName()
		        		);		   
		        System.out.println();
	            System.out.println("acquire,c," + atividade.getRObjectId());
	            System.out.println("delegate,c,"+ atividade.getRObjectId()+",'"+atividade.getRPerformerName()+"'");
	            System.out.println("save,c,"+ atividade.getRObjectId());
		    }
		    System.out.println("----------------------------------------------------------------------------------------------");

		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirEmLote(ArrayList<String> protocolos) {

	}
 
	public void historicoEmLote(ArrayList<String> protocolos) {
		
		int i = 0;
		for(i = 0; i < protocolos.size(); i++){
			System.out.println(protocolos.get(i));
			try {
	
				Protocolo historico = rep.getHistoricoEmLote(protocolos.get(i));
				
				
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
