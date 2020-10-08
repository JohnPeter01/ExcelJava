package br.com.indra.documentum.program;

import br.com.indra.documentum.service.ProcessHandler;
import br.com.indra.documentum.utils.Utils;

public class ExcellRunner {
	public static void main(String[] args) {
		ProcessHandler processHandler = new ProcessHandler();
		
		Utils utils = new Utils();
		
		int processo = Integer.parseInt(args[0]);
		
		switch (processo) {
		case 1:
			processHandler.buscaHistorico();
			break;
			
		case 2:
			
			processHandler.historicoEmLote(utils.lerArquivo(args[1]));
			break;
		
		case 3:
			processHandler.delegarEmLote(utils.lerArquivo(args[1]));
			break;
			
		case 4:
			processHandler.excluirEmLote(utils.lerArquivo(args[1]));
			break;
			
		default:
			System.out.println("Fun��o n�o localizada.");
			break;
		}
	}

}
