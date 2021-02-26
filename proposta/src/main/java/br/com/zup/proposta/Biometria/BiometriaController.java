package br.com.zup.proposta.Biometria;

import java.net.URI;
import java.util.HashMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;

@RestController
public class BiometriaController {

	private CartaoRepository cartaoRepository;

	private BiometriaRepository biometriaRepository;

	private final Logger logger = LoggerFactory.getLogger(Biometria.class);

	public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {

		this.cartaoRepository = cartaoRepository;
		this.biometriaRepository = biometriaRepository;
	}

	@PostMapping("/cartoes/{id}/biometrias")
	public ResponseEntity<?> cadastro(@PathVariable Long id, @RequestBody @Valid NovaBiometriaRequest request,
			UriComponentsBuilder uriBuilder) {

		Cartao cartao = cartaoRepository.findAllById(id);

		if (cartao == null) {
			return ResponseEntity.notFound().build();
		}

		if (!request.estaEm64Bits()) {
			HashMap<String, Object> resposta = new HashMap<>();

			resposta.put("mensagem", "Biometria inválida!");

			return ResponseEntity.badRequest().body(resposta);
		}
		Biometria biometria = new Biometria(request.getBiometria(), cartao);
		biometriaRepository.save(biometria);

		URI location = uriBuilder.path("/cartoes/{idCartao}/biometrias/{idBiometria}")
				.buildAndExpand(cartao.getId(), biometria.getId()).toUri();

		logger.info("Biometria criada para o cartao={} com sucesso!", cartao.getNumero());
		return ResponseEntity.created(location).build();
	}

}
