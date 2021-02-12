package br.com.zup.proposta.novaproposta;

import java.net.URI;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/propostas")
public class NovaPropostaController {

	private final PropostaRepository propostaRepository;
	private final AnaliseClient analiseClient;

	public NovaPropostaController(PropostaRepository propostaRepository, AnaliseClient analiseClient) {
		this.propostaRepository = propostaRepository;
		this.analiseClient = analiseClient;
	}

	@PostMapping
	public ResponseEntity<?> cadastro(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) {

		if (propostaRepository.existsByDocumento(request.getDocumento())) {
			HashMap<String, Object> resposta = new HashMap<>();

			resposta.put("mensagem", "Já existe documento cadastrado");

			return ResponseEntity.unprocessableEntity().body(resposta);
		}
		Proposta proposta = request.toModel();

		propostaRepository.save(proposta);

		try {
			analiseClient.consulta(new AnaliseClient.ConsultaStatusRequest(proposta));
			proposta.atualizaStatus(Status.ELEGIVEL);
			
		} catch (FeignException.UnprocessableEntity ex) {
			
			proposta.atualizaStatus(Status.NAO_ELEGIVEL);
			
		}

		propostaRepository.save(proposta);

		URI location = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
