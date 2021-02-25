package br.com.zup.proposta.bloqueio;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.EnumStatusCartao;
import br.com.zup.proposta.novaproposta.CartaoClient;
import feign.FeignException;

@RestController

public class BloqueioCartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BloqueioCartaoRepository bloqueioCartaoRepository;

	@Autowired
	private CartaoClient cartaoClient;

	private final Logger logger = LoggerFactory.getLogger(BloqueioCartao.class);

	@PostMapping("/api/cartoes/{id}/bloqueios")
	public ResponseEntity<?> bloqueio(@PathVariable Long id, HttpServletRequest request) {

		Optional<Cartao> cartao = cartaoRepository.findById(id);
		Optional<BloqueioCartao> cartoesBloqueados = bloqueioCartaoRepository.findById(id);
		if (cartao.isPresent()) {

			if (cartoesBloqueados.isPresent()) {
				return ResponseEntity.unprocessableEntity().body("Cartão já bloqueado");
			}
			try {
				cartaoClient.bloqueio(cartao.get().getNumero(), new BloqueioCartaoRequest("proposta"));

			} catch (FeignException.UnprocessableEntity ex) {
				Map<String, Object> badRequest = new HashMap<>();
				badRequest.put("Algum erro interno foi violado", "Cartão já foi bloqueado!");
				return ResponseEntity.unprocessableEntity().body(badRequest);
			}

			BloqueioCartao bloqueioCartao = new BloqueioCartao(cartao.get(), request.getRemoteAddr(),
					request.getHeader("User-Agent"));

			bloqueioCartaoRepository.save(bloqueioCartao);

			cartao.get().atualizaStatusCartao(EnumStatusCartao.BLOQUEADO);
			cartaoRepository.save(cartao.get());

			logger.info("Bloqueio realizado para cartao={}!", cartao.get().getNumero());
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();
	}

}
