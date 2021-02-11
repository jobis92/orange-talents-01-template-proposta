package br.com.zup.proposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/propostas")
public class NovaPropostaController {

	private final PropostaRepository propostaRepository;

	public NovaPropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@PostMapping
	public ResponseEntity<?> cadastro(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		Proposta proposta = request.toModel();

		propostaRepository.save(proposta);

		URI location = uriBuilder.path("/api/propostas/{id}")
				.buildAndExpand(proposta.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
