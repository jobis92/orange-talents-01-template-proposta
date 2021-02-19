package br.com.zup.proposta.novaproposta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;

	private String complemento;

	@Deprecated
	Endereco() {
	}

	public Endereco(@NotBlank String cep, @NotBlank String logradouro, @NotBlank String numero, String complemento) {

		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}
	
	

}
