package br.com.zup.proposta.novaproposta;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.novaproposta.CartaoClient.ConsultaCartaoResponse;
import feign.FeignException;

@RestController
@RequestMapping(value = "/api/propostas")
public class NovaPropostaController {

	private final PropostaRepository propostaRepository;
	private final AnaliseClient analiseClient;
	private final CartaoClient cartaoClient;

	public NovaPropostaController(PropostaRepository propostaRepository, AnaliseClient analiseClient,
			CartaoClient cartaoClient) {
		this.propostaRepository = propostaRepository;
		this.analiseClient = analiseClient;
		this.cartaoClient = cartaoClient;
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
			proposta.atualizaStatus(EnumStatus.ELEGIVEL);

		} catch (FeignException.UnprocessableEntity ex) {
			proposta.atualizaStatus(EnumStatus.NAO_ELEGIVEL);

		}
		propostaRepository.save(proposta);

		vinculaCartaoProposta();

		URI location = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@Scheduled(fixedDelay = 5000)
	public void vinculaCartaoProposta() {

		List<Proposta> lista = propostaRepository.findByStatusAndCartaoNull(EnumStatus.ELEGIVEL);
		for (Proposta proposta : lista) {
			try {

				ConsultaCartaoResponse resposta = cartaoClient.consulta(proposta.getId().toString());
				
				if (resposta.getId() != null) {
					proposta.vincularCartao(resposta);
					propostaRepository.save(proposta);
					
				}
			} catch (FeignException.InternalServerError e) {
				System.out.println("Cartão não existente para a proposta" + proposta.getId());
			}

		}

	}

}