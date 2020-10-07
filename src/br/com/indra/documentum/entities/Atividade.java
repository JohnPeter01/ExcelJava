package br.com.indra.documentum.entities;

public class Atividade {
	private String rObjectId;
	private String IdWorkflow;
	private String rPerformerName;
	
	public Atividade() {
		super();
	}

	public Atividade(String objectId,String IdWorkflow, String performerName) {
		this.IdWorkflow = IdWorkflow;
		this.rObjectId = objectId;
		this.rPerformerName = performerName;
	}

	public String getRObjectId() {
		return rObjectId;
	}

	public void setRObjectId(String objectId) {
		rObjectId = objectId;
	}

	public String getRPerformerName() {
		return rPerformerName;
	}

	public void setRPerformerName(String performerName) {
		rPerformerName = performerName;
	}

	public String getIdWorkflow() {
		return IdWorkflow;
	}

	public void setIdWorkflow(String idWorkflow) {
		IdWorkflow = idWorkflow;
	}

}
