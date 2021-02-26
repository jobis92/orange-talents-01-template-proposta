package br.com.zup.proposta.aviso;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

public class NovoAvisoRequest {

	@NotBlank
	private String destino;

	@Future
	private LocalDate validoAte;

	@Deprecated
	public NovoAvisoRequest() {
	}

	public NovoAvisoRequest(@NotBlank String destino, @Future LocalDate validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}



	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

}
