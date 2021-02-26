package br.com.zup.proposta.carteira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaCarteiraRequest {

	@JsonProperty
	private String email;

	@JsonProperty
	private String carteira;

	public NovaCarteiraRequest(CarteiraRequest request) {
		this.email = request.getEmail();
		this.carteira = request.getCarteira().toString();
	}

}
