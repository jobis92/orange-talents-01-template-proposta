package br.com.zup.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.validacao.CpfOuCnpj;

public class NovaPropostaRequest {

	@CpfOuCnpj
	@NotBlank
	private String documento;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@Positive
	@NotNull
	private BigDecimal salario;

	@NotNull
	private EnderecoRequest endereco;

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public Proposta toModel() {
		return new Proposta(documento, email, nome, salario, new Endereco(endereco.getCep(), endereco.getLogradouro(),
				endereco.getNumero(), endereco.getComplemento()));
	}

}
