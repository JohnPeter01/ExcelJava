package br.com.indra.documentum.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.indra.documentum.connection.ConexaoDocumentum;
import br.com.indra.documentum.entities.Atividade;
import br.com.indra.documentum.entities.Protocolo;
import br.com.indra.documentum.entities.Workflow;
import br.com.indra.documentum.queries.Querys;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfTypedObject;

public class DocumentumRepository extends ConexaoDocumentum {

	public DocumentumRepository() {
		super();
	}

	public DocumentumRepository(String Usuario_, String Senha_,
			String Repositorio_) {
		super(Usuario_, Senha_, Repositorio_);
	}

	/**
	 * @since 29/09/2020
	 * @param Cabinet_name
	 *            : Nome do Cabinet que ser� criado
	 */
	public void createCabinet(String Cabinet_name) throws Exception {

		IDfFolder cabinet = (IDfFolder) getSessDctm().newObject("dm_cabinet");

		if (cabinet != null) {

			cabinet.setObjectName(Cabinet_name);
			cabinet.save();

			System.out.println("Cabinet criado com sucesso");

		}

	}

	/**
	 * @since 29/09/2020
	 * @param Cabinet_link
	 *            : Path do Documentum onde ser� criada a pasta.
	 * @param folder_name
	 *            : nome da pasta no documentum.
	 */
	public void createFolder(String Cabinet_link, String folder_name)
			throws Exception {

		IDfFolder folder = (IDfFolder) getSessDctm().newObject("dm_folder");
		ArrayList<String> pastas = this.ConsultarQuery(Querys
				.VerificaPasta(folder_name));
		if (folder != null && pastas.size() == 0) {

			folder.setObjectName(folder_name);

			folder.link("/" + Cabinet_link);

			folder.save();

			System.out.println("Pasta criada com sucesso no cabinet"
					+ Cabinet_link);

		} else {
			System.out.println("PASTA JA EXISTENTE");
		}
	}

	/**
	 * @since 29/09/2020
	 * @param Nome_doc
	 *            : nome do Documento.
	 * @param tipo_conteudo
	 *            : tipo do documento(txt,PDF etc..). tipo - crtext...
	 * @param path_conteudo
	 *            : path onde o documento est� localizado em sua maquina.
	 * @param documentum_path
	 *            : path no documentum de onde ser� disponibilizado o arquivo.
	 * 
	 */
	public IDfDocument createDocument(String Nome_doc, String tipo_conteudo,
			String path_conteudo, String documentum_path) throws Exception {

		IDfDocument document = (IDfDocument) getSessDctm().newObject(
				"dm_document");

		if (document != null) {

			document.setObjectName(Nome_doc);

			document.setContentType(tipo_conteudo);

			document.setFile(path_conteudo);

			document.link(documentum_path);

			document.save();

		}

		return document;

	}

	/**
	 * Este M�todo permite qualquer criacao de Objeto
	 * 
	 * @since 29/09/2020
	 * @param Nome_doc
	 *            : nome do Documento.
	 * @param doc_type
	 *            : tipo do documento(Objeto documentum)
	 * @param tipo_conteudo
	 *            : tipo do conteudo(crtext e etc).
	 * @param path_conteudo
	 *            : path no documentum de onde ser� disponibilizado o arquivo.
	 * @param documentum_path
	 *            : path no documentum de onde ser� disponibilizado o arquivo.
	 */
	public IDfDocument createPasta30(String Nome_doc, String doc_type,
			String tipo_conteudo, String path_conteudo, String documentum_path)
			throws Exception {

		IDfDocument document = (IDfDocument) getSessDctm().newObject(doc_type);

		if (document != null) {

			document.setObjectName(Nome_doc);

			document.setContentType(tipo_conteudo);

			document.link(documentum_path);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = dateFormat.format(new Date());

			document.setString("date_controler", strDate);

			document.save();

		}
		System.out.println(document.getString("date_controler"));
		return document;

	}

	/**
	 * Este M�todo permite qualquer criacao de Objeto
	 * 
	 * @since 29/09/2020
	 * @param Nome_doc
	 *            : nome do Documento.
	 * @param doc_type
	 *            : tipo do documento(Objeto documentum)
	 * @param tipo_conteudo
	 *            : tipo do conteudo(crtext e etc).
	 * @param path_conteudo
	 *            : path no documentum de onde ser� disponibilizado o arquivo.
	 * @param documentum_path
	 *            : path no documentum de onde ser� disponibilizado o arquivo.
	 */
	public IDfDocument createPasta60(String Nome_doc, String doc_type,
			String tipo_conteudo, String path_conteudo, String documentum_path,
			String data) throws Exception {

		IDfDocument document = (IDfDocument) getSessDctm().newObject(doc_type);

		if (document != null) {

			document.setObjectName(Nome_doc);

			document.setContentType(tipo_conteudo);

			document.link(documentum_path);

			document.setString("date_controler", data);

			document.save();

		}
		System.out.println(document.getString("date_controler"));
		return document;

	}

	/**
	 * Este M�todo faz uma consulta DQL no Documentum
	 * 
	 * @since 29/09/2020
	 * @param queryString
	 *            : recebe uma Query DQL.
	 */
	// -------------------CONSULTAS DQL--------------------
	public ArrayList<String> ConsultarQuery(String queryString)
			throws Exception {
		System.out.println("PRINT CONSULTAR QUERY");
		System.out.println(queryString);
		ArrayList<String> arquivo = new ArrayList<String>();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();

			System.out
					.println("----------------------------------------------------");
			System.out.println("resultado: "
					+ typeObject.getString("resultado_query"));
			System.out.println("creation date "
					+ typeObject.getString("r_object_id"));
			System.out
					.println("----------------------------------------------------");

			arquivo.add(typeObject.getString("resultado_query"));
		}

		if (coll != null)

			coll.close();

		return arquivo;

	}

	/**
	 * Este M�todo faz uma consulta DQL no Documentum
	 * 
	 * @since 29/09/2020
	 * @param queryString
	 *            : recebe uma Query DQL pr�-selecionada.
	 */
	public boolean ConsultarPasta(String queryString) throws Exception {
		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		boolean pastaExiste = false;

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();

			System.out
					.println("----------------------------------------------------");
			System.out.println("resultado: "
					+ typeObject.getString("resultado_query"));
			System.out
					.println("----------------------------------------------------");

			String arquivo = typeObject.getString("resultado_query");
			pastaExiste = Integer.parseInt(arquivo) > 0;

		}

		if (coll != null)

			coll.close();

		return pastaExiste;

	}

	/**
	 * Este M�todo faz uma consulta DQL no Documentum
	 * 
	 * @since 29/09/2020
	 * @param queryString
	 *            : recebe uma Query DQL pr�-selecionada.
	 */
	public ArrayList<String> ConsultarQueryData(String queryString)
			throws Exception {
		ArrayList<String> arquivo = new ArrayList<String>();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();

			System.out
					.println("----------------------------------------------------");
			System.out.println("resultado: "
					+ typeObject.getString("r_object_id"));
			System.out.println("resultado: "
					+ typeObject.getString("object_name"));
			System.out.println("creation date "
					+ typeObject.getString("r_creation_date"));
			System.out
					.println("----------------------------------------------------");

			arquivo.add(typeObject.getString("r_object_id"));
			arquivo.add(typeObject.getString("object_name"));
			arquivo.add(typeObject.getString("r_creation_date"));
		}

		if (coll != null)

			coll.close();

		return arquivo;

	}

	/**
	 * Este M�todo faz um UPDATE DQL no Documentum
	 * 
	 * @since 29/09/2020
	 * @param queryString
	 *            : recebe uma Query DQL pr�-selecionada(classe Queryes).
	 */
	public void ConsultarQueryUPDATE(String queryString) throws Exception {

		System.out.println(queryString);
		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		query.execute(getSessDctm(), 0);

	}

	/**
	 * Este M�todo verifica se existem nomes Nulos nos arquivos. Se tiver nomes
	 * nulos, move para pasta "Parametros Incorretos".
	 * 
	 * @since 29/09/2020
	 */
	public void InvalidObjectNameZero() throws Exception {

		IDfQuery query = new DfQuery();

		query.setDQL(Querys.MoveFileNameNull("Nao_Indexados_TESTE"));

		IDfCollection collNotName = query.execute(getSessDctm(), 0);

		while (collNotName.next()) {

			System.out.println("ENTREI NA FUNCAO 0");
			IDfTypedObject typeObject = (IDfTypedObject) collNotName
					.getTypedObject();

			System.out
					.println("========" + typeObject.getString("r_object_id"));

			ConsultarQueryUPDATE(Querys.UPDATE_LINK_NAME_NULL(
					"/teste_pasta_reindex/ParametrosIncorretos", typeObject
							.getString("r_object_id")));
			ConsultarQueryUPDATE(Querys.UPDATE_UNLINK_NAME_NULL(
					"/teste_pasta_reindex/Nao_Indexados_TESTE", typeObject
							.getString("r_object_id")));

		}

	}

	/**
	 * Metodo faz uma query e Atribui o object_name ao Array
	 * 
	 * @param String
	 *            pasta: Path da pasta onde ser� buscado os arquivos no
	 *            Documentum
	 */
	public ArrayList<String> BuscaArquivosPasta(String pasta) throws Exception {
		String queryString = Querys.PastaParaArquivo(pasta);

		ArrayList<String> arquivo = new ArrayList<String>();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();

			String objectNameFile = typeObject.getString("resultado_query");

			arquivo.add(objectNameFile);

		}

		if (coll != null)

			coll.close();

		return arquivo;

	}

	public Protocolo getHistorico(String protocolo) throws Exception {

		String queryString = Querys.buscaHistorico(protocolo);

		Protocolo protocolo1 = new Protocolo();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
			protocolo1 = new Protocolo(typeObject.getString("r_object_id"),
					typeObject.getString("nome"),
					typeObject.getString("situacao"),
					typeObject.getString("id_workflow"));

			

		}

		return protocolo1;

	}
	
	public ArrayList<Workflow> getWorkflow(String wrokflow) throws Exception {

		String queryString = Querys.buscaWorkflow(wrokflow);

		ArrayList<Workflow> returnArray = new ArrayList<Workflow>();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
			
			Workflow objectNameFile = new Workflow(typeObject.getString("r_object_id"),
					typeObject.getString("nome_atividade"),
					typeObject.getString("nome_responsavel"),
					typeObject.getString("data_inicio"),
					typeObject.getString("data_termino"),
					typeObject.getString("tempo_solucao"),
					typeObject.getString("tempo_total")		
					);

			returnArray.add(objectNameFile);

		}

		return returnArray;

	}
	
	public Atividade getAtividade(String wrokflow) throws Exception {

		String queryString = Querys.buscaAtividades(wrokflow);

		Atividade atividade = new Atividade();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
			
			atividade = new Atividade(
					wrokflow,
					typeObject.getString("r_object_id"),
					typeObject.getString("r_performer_name"));

		}

		return atividade;

	}
	
	
public ArrayList<Folder> getFdAero(String protocolo) throws DfException {
		
		String queryString = Querys.pastaTypeFdAero(protocolo);

		ArrayList<Folder> returnArray = new ArrayList<Folder>();

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		
		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
			
			Folder fd  = new Folder(
					typeObject.getString("r_object_id"),
					typeObject.getString("object_name")	
					);

			returnArray.add(fd);
		}
		

		
		return returnArray;
	}

	public Folder getTpAero(String nomeFolder)  {
		
		Folder folder = new  Folder();
		try {
		String queryString = Querys.pastaTypeTpAero(nomeFolder);

		

		IDfQuery query = new DfQuery();

		query.setDQL(queryString);

		IDfCollection coll = query.execute(getSessDctm(), 0);

		
		while (coll.next()) {

			IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
			
			folder = new Folder(
					typeObject.getString("r_object_id"),
					typeObject.getString("object_name")	
					);

		
			
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return folder;
		
	
	}
	
	
	
	public Protocolo getHistoricoEmLote(String protocolo) throws Exception {

		 

        String queryString = Querys.buscaHistorico(protocolo);

 

        Protocolo protocolo1 = new Protocolo();

 

        IDfQuery query = new DfQuery();

 

        query.setDQL(queryString);

 

        IDfCollection coll = query.execute(getSessDctm(), 0);

 

        while (coll.next()) {

 

            IDfTypedObject typeObject = (IDfTypedObject) coll.getTypedObject();
            protocolo1 = new Protocolo(typeObject.getString("r_object_id"),
                    typeObject.getString("nome"),
                    typeObject.getString("situacao"),
                    typeObject.getString("id_workflow"));

 

            

 

        }

 

        return protocolo1;

 

    }
	
	
	
	
}
