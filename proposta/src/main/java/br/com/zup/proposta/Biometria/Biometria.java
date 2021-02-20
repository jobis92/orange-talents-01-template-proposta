package br.com.zup.proposta.Biometria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
@Table(name = "biometria")
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String biometria;

	@NotNull
	@ManyToOne
	private @NotNull Cartao cartao;

	public Biometria(@NotBlank String biometria, @NotNull Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getBiometria() {
		return biometria;
	}

}
