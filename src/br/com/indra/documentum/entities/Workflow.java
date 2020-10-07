package br.com.indra.documentum.entities;

public class Workflow {

	private String rObjectId;
	private String nomeAtividade;
	private String nomeResponsavel;
	private String dataInicio;
	private String dataTermino;
	private String tempoSolucao;
	private String tempoTotal;

	public Workflow(String robjectId, String nomeAtividade,
			String nomeResponsavel, String dataInicio, String dataTermino,
			String tempoSolucao, String tempoTotal) {

		this.rObjectId = robjectId;
		this.nomeAtividade = nomeAtividade;
		this.nomeResponsavel = nomeResponsavel;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.tempoSolucao = tempoSolucao;
		this.tempoTotal = tempoTotal;
	}

	public String getRObjectId() {
		return rObjectId;
	}

	public void setRObjectId(String objectId) {
		rObjectId = objectId;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getTempoSolucao() {
		return tempoSolucao;
	}

	public void setTempoSolucao(String tempoSolucao) {
		this.tempoSolucao = tempoSolucao;
	}

	public String getTempoTotal() {
		return tempoTotal;
	}

	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}

	@Override
	public String toString() {
		return this.rObjectId + " " + this.nomeAtividade + " "
				+ this.nomeResponsavel + " " + this.dataInicio + " "
				+ this.dataTermino + " " + this.tempoSolucao + " "
				+ this.tempoTotal;
	}

}
