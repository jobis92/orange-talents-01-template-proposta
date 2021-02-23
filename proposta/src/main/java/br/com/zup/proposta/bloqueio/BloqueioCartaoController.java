package br.com.zup.proposta.bloqueio;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping(value = "/api/bloqueio")
public class BloqueioCartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BloqueioCartaoRepository bloqueioCartaoRepository;

	private final Logger logger = LoggerFactory.getLogger(BloqueioCartao.class);

	@PostMapping("/{idCartao}")
	public ResponseEntity<?> bloqueio(@PathVariable Long idCartao, UriComponentsBuilder uriBuilder,
			HttpServletRequest request) {

		Cartao cartao = cartaoRepository.findAllById(idCartao);
		Optional<BloqueioCartao> cartoesBloqueados = bloqueioCartaoRepository.findById(idCartao);
		if (cartao == null) {
			return ResponseEntity.notFound().build();
		} else if (cartoesBloqueados.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		} else {

			BloqueioCartao bloqueioCartao = new BloqueioCartao(cartao.getId(), request.getRemoteAddr(),
					request.getHeader("User-Agent"));

			bloqueioCartaoRepository.save(bloqueioCartao);

			URI location = uriBuilder.path("/{idCartao}").buildAndExpand(bloqueioCartao.getId()).toUri();

			logger.info("Bloqueio realizado para cartao={}!", cartao.getNumero());
			return ResponseEntity.created(location).build();
		}

	}

}
