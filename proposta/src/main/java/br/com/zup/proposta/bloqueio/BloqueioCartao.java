package br.com.zup.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bloqueios")
public class BloqueioCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long cartaoId;

	private LocalDateTime dataBloqueio;

	private String ip;

	private String userAgent;

	public BloqueioCartao(@NotNull Long cartaoId, String ip, String userAgent) {
		this.cartaoId = cartaoId;
		this.dataBloqueio = LocalDateTime.now();
		this.ip = ip;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public Long getCartao() {
		return cartaoId;
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
