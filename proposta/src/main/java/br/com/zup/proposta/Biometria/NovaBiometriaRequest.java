package br.com.zup.proposta.Biometria;

import javax.validation.constraints.NotBlank;

import org.apache.commons.codec.binary.Base64;

public class NovaBiometriaRequest {

	@NotBlank
	private String biometria;

	public NovaBiometriaRequest(@NotBlank String biometria) {

		this.biometria = biometria;

	}

	public String getBiometria() {
		return biometria;
	}

	public boolean estaEm64Bits() {

		return Base64.isBase64(biometria.getBytes());
	}

}
