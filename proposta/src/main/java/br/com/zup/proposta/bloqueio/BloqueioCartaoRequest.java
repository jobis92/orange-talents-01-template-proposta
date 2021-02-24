package br.com.zup.proposta.bloqueio;

public class BloqueioCartaoRequest {

	private String sistemaResponsavel;

	@Deprecated
	public BloqueioCartaoRequest() {
	}

	public BloqueioCartaoRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
