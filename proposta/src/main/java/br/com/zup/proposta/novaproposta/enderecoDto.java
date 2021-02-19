package br.com.zup.proposta.novaproposta;

public class enderecoDto {

	private String cep;

	private String logradouro;

	private String numero;

	private String complemento;

	public enderecoDto(Endereco endereco) {

		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
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
