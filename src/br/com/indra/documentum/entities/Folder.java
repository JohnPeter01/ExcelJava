package br.com.indra.documentum.entities;

public class Folder {

	private String rObjectId;
	
	private String objectName; 
	

	
	private String apiComandos;
	
	public Folder(String rObjectId, String objectName ){
		super();
		this.setObjectName(objectName);
		this.setRObjectId(rObjectId);
		
		
	}
	
	public void apiComandos(String rObjectId) {
		this.apiComandos = apiComandos;
	}


	public String apiComandos() {
		return apiComandos;
	}

	

	public Folder() {
		// TODO Auto-generated constructor stub
	}


	public void setRObjectId(String rObjectId) {
		this.rObjectId = rObjectId;
	}


	public String getRObjectId() {
		return rObjectId;
	}


	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}


	public String getObjectName() {
		return objectName;
	}
	
}
