package br.com.zup.proposta.novaproposta;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.validacao.CpfOuCnpj;

@Entity
@Table(name = "propostas")
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	@Embedded
	private Endereco endereco;

	@Deprecated
	Proposta() {

	}

	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@Positive @NotNull BigDecimal salario, Endereco endereco) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;

	}

	public Long getId() {
		return id;
	}

}
