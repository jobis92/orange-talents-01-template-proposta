package br.com.zup.proposta.novaproposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropostaRepository extends JpaRepository<Proposta, Long>, JpaSpecificationExecutor<Proposta> {

	boolean existsByDocumento(String documento);

	List<Proposta> findByStatusAndCartaoNull(EnumStatus status);

}
