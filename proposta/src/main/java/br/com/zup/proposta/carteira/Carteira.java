package br.com.zup.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
@Table(name = "carteiras")
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Email
	@NotBlank
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumCarteira enumCarteira;

	@Deprecated
	public Carteira() {
	}

	public Carteira(@NotNull Cartao cartao, @Email @NotBlank String email, @NotNull EnumCarteira enumCarteira) {
		this.cartao = cartao;
		this.email = email;
		this.enumCarteira = enumCarteira;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getEmail() {
		return email;
	}

	public EnumCarteira getEnumCarteira() {
		return enumCarteira;
	}

	@Override
	public String toString() {
		return "Carteira [cartao=" + cartao + ", email=" + email + ", enumCarteira=" + enumCarteira + "]";
	}

}
