package br.com.zup.proposta.novaproposta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.novaproposta.CartaoClient.ConsultaCartaoResponse;
import feign.FeignException;

@Component
public class VinculaCartaoProposta {

	private CartaoClient cartaoClient;

	private PropostaRepository propostaRepository;

	public VinculaCartaoProposta(CartaoClient cartaoClient, PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
		this.cartaoClient = cartaoClient;
	}

	@Transactional
	@Scheduled(fixedDelay = 5000)
	void vinculaCartaoProposta() {

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
