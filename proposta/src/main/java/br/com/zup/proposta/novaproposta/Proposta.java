package br.com.zup.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.novaproposta.CartaoClient.ConsultaCartaoResponse;
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

	@Enumerated(EnumType.STRING)
	private EnumStatus status;

	private String idCartao;

	@OneToOne(mappedBy = "proposta", cascade = CascadeType.ALL)
	private Cartao cartao;

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

	
	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", salario="
				+ salario + ", endereco=" + endereco + ", status=" + status + ", idCartao=" + idCartao + ", cartao="
				+ cartao + "]";
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}
	
	

	public String getEmail() {
		return email;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public String getIdCartao() {
		return idCartao;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void atualizaStatus(EnumStatus status) {
		this.status = status;
	}

	public EnumStatus getStatus() {

		return status;
	}

	public void vincularCartao(ConsultaCartaoResponse response) {
		 this.cartao = new Cartao(response,this);
		 this.idCartao = response.getId();
	}
}
