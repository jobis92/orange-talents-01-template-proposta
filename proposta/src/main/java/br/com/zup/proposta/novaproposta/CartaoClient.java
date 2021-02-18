package br.com.zup.proposta.novaproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartaoCliente", url = "http://localhost:8888")
public interface CartaoClient {

	@GetMapping(value = "/api/cartoes/")
	ConsultaCartaoResponse consulta(@RequestParam String idProposta);

	class ConsultaCartaoResponse {
		private String id;

		private String numero;

		private LocalDateTime emitidoEm;

		private String titular;

		private BigDecimal limite;

		@Override
		public String toString() {
			return "ConsultaCartaoResponse [id=" + id + ", numero=" + numero + ", emitidoEm=" + emitidoEm + ", titular="
					+ titular + ", limite=" + limite + "]";
		}

		public String getId() {
			return id;
		}

		public String getNumero() {
			return numero;
		}

		public LocalDateTime getEmitidoEm() {
			return emitidoEm;
		}

		public String getTitular() {
			return titular;
		}

		public BigDecimal getLimite() {
			return limite;
		}

	}

}
