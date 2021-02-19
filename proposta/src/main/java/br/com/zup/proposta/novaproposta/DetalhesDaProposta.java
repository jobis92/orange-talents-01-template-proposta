package br.com.zup.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class DetalhesDaProposta {

	private String documento;
	private String email;
	private String nome;
	private BigDecimal salario;
	private enderecoDto endereco;
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	private String idCartao;

	public DetalhesDaProposta(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.salario = proposta.getSalario();
		this.endereco = new enderecoDto(proposta.getEndereco());
		this.status = proposta.getStatus();
		this.idCartao = proposta.getIdCartao();
	}

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

	public enderecoDto getEndereco() {
		return endereco;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public String getIdCartao() {
		return idCartao;
	}

}
