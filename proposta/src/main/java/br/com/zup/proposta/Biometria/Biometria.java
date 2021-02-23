package br.com.zup.proposta.Biometria;

import java.time.LocalDateTime;

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

	private LocalDateTime dataBiometria;



	@NotNull
	@ManyToOne
	private @NotNull Cartao cartao;

	public Biometria(@NotBlank String biometria, @NotNull Cartao cartao, LocalDateTime dataBiometria) {
		this.biometria = biometria;
		this.cartao = cartao;
		this.dataBiometria = dataBiometria;
	}

	public Long getId() {
		return id;
	}

	public String getBiometria() {
		return biometria;
	}
	
	public LocalDateTime getDataBiometria() {
		return dataBiometria;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
