package br.com.zup.proposta.novaproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "${analise.host}")
public interface AnaliseClient {

	@PostMapping()
	ConsultaStatusRequest consulta(@RequestBody ConsultaStatusRequest consultaStatusRequest);

	class ConsultaStatusRequest {
		private String documento;
		private String nome;
		private Long idProposta;

		@Deprecated
		public ConsultaStatusRequest() {

		}

		public ConsultaStatusRequest(Proposta proposta) {

			this.documento = proposta.getDocumento();
			this.nome = proposta.getNome();
			this.idProposta = proposta.getId();
		}

		public String getDocumento() {
			return documento;
		}

		public String getNome() {
			return nome;
		}

		public Long getIdProposta() {
			return idProposta;
		}

	}
}
