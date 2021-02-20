package br.com.zup.proposta.Biometria;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.apache.commons.codec.binary.Base64;

public class NovaBiometriaRequest {

	@NotBlank
	private String biometria;

	private LocalDateTime dataCadastro;

	public NovaBiometriaRequest(@NotBlank String biometria, LocalDateTime dataCadastro) {

		this.biometria = biometria;
		this.dataCadastro = LocalDateTime.now();
	}

	public String getBiometria() {
		return biometria;
	}

	public boolean estaEm64Bits() {

		return Base64.isBase64(biometria.getBytes());
	}

	public LocalDateTime getDataCadastro() {

		return dataCadastro;
	}

}
