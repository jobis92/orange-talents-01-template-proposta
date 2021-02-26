package br.com.zup.proposta.carteira;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.proposta.cartao.Cartao;


public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

	Optional<Carteira> findByEnumCarteiraAndCartao(EnumCarteira enumCarteira, Cartao cartao);

}
