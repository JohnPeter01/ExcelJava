package br.com.indra.documentum.entities;

public class Protocolo {
	private String rObjectId;
	private String nome;
	private String situacao;
	private String idWorkflow;
	
	
	
	public Protocolo() {
		super();
	}



	public Protocolo(String rObjectId,String nome,String situacao,String idWorkflow
			) {
		super();
		this.idWorkflow = idWorkflow;
		this.nome = nome;
		this.rObjectId = rObjectId;
		this.situacao = situacao;
	}



	public String getRObjectId() {
		return rObjectId;
	}



	public void setRObjectId(String rObjectId) {
		this.rObjectId = rObjectId;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getSituacao() {
		return situacao;
	}



	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}



	public String getIdWorkflow() {
		return idWorkflow;
	}



	public void setIdWorkFlow(String idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
	
}
