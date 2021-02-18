package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.zup.proposta.novaproposta.CartaoClient.ConsultaCartaoResponse;
import br.com.zup.proposta.novaproposta.Proposta;

@Entity
@Table(name = "cartao")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	private LocalDateTime emitidoEm;

	private String titular;

	private BigDecimal limite;

	@OneToOne
	private Proposta proposta;

	@Deprecated
	public Cartao() {
	}

	public Cartao(ConsultaCartaoResponse response, Proposta proposta) {
		this.numero = response.getId();
		this.emitidoEm = response.getEmitidoEm();
		this.titular = response.getTitular();
		this.limite = response.getLimite();
		this.proposta = proposta;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numero=" + numero + ", emitidoEm=" + emitidoEm + ", titular=" + titular
				+ ", limite=" + limite + ", proposta=" + proposta + "]";
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

}
