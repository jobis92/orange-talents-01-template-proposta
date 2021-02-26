package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.zup.proposta.Biometria.Biometria;
import br.com.zup.proposta.aviso.Aviso;
import br.com.zup.proposta.bloqueio.BloqueioCartao;
import br.com.zup.proposta.carteira.Carteira;
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

	@Enumerated(EnumType.STRING)
	private EnumStatusCartao statusCartao;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Biometria> biometrias = new ArrayList<>();

	@OneToOne(mappedBy = "cartao", cascade = CascadeType.ALL)
	private BloqueioCartao bloqueio;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Aviso> avisoViagem = new ArrayList<>();

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Carteira> carteiras = new ArrayList<>();

	@Deprecated
	public Cartao() {
	}

	public Cartao(ConsultaCartaoResponse response, Proposta proposta) {
		this.numero = response.getId();
		this.emitidoEm = response.getEmitidoEm();
		this.titular = response.getTitular();
		this.limite = response.getLimite();
		this.proposta = proposta;
		this.statusCartao = EnumStatusCartao.DESBLOQUEADO;
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

	public EnumStatusCartao getStatusCartao() {
		return statusCartao;
	}

	public void atualizaStatusCartao(EnumStatusCartao statusCartao) {
		this.statusCartao = statusCartao;
	}

	public void atualizaAviso(Aviso aviso) {
		this.avisoViagem.add(aviso);
	}

	public void adicionaCarteiraDigital(Carteira carteiraDigital) {
		this.carteiras.add(carteiraDigital);
	}
}
