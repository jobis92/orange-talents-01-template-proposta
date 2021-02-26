package br.com.zup.proposta.novaproposta;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.metricas.MinhasMetricas;
import feign.FeignException;

@RestController
@RequestMapping(value = "/api/propostas")
public class NovaPropostaController {

	private final PropostaRepository propostaRepository;
	private final AnaliseClient analiseClient;
	private final VinculaCartaoProposta cartaoProposta;
	private final MinhasMetricas minhasMetricas;

	private final Logger logger = LoggerFactory.getLogger(Proposta.class);

	public NovaPropostaController(PropostaRepository propostaRepository, AnaliseClient analiseClient,
			VinculaCartaoProposta cartaoProposta, @Lazy MinhasMetricas minhasMetricas) {
		this.propostaRepository = propostaRepository;
		this.analiseClient = analiseClient;
		this.cartaoProposta = cartaoProposta;
		this.minhasMetricas = minhasMetricas;
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

		cartaoProposta.vinculaCartaoProposta();

		URI location = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

		minhasMetricas.meuContador();

		logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getDocumento(),
				proposta.getSalario());
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{idProposta}")
	public ResponseEntity<?> consulta(@PathVariable Long idProposta) {
		Optional<Proposta> proposta = propostaRepository.findById(idProposta);
		if (proposta.isPresent()) {

//			minhasMetricas.meuTimer(idProposta);
			return ResponseEntity.ok(new DetalhesDaProposta(proposta.get()));

		}

		return ResponseEntity.notFound().build();

	}

}
