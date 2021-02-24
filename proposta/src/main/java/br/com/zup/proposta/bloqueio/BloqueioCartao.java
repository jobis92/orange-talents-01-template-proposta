package br.com.zup.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
@Table(name = "bloqueios")
public class BloqueioCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

	private LocalDateTime dataBloqueio;

	private String ip;

	private String userAgent;

	@Deprecated
	public BloqueioCartao() {
	}

	public BloqueioCartao(@NotNull Cartao cartao, String ip, String userAgent) {
		this.cartao = cartao;
		this.dataBloqueio = LocalDateTime.now();
		this.ip = ip;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	
	
}
