package br.com.zup.proposta.aviso;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
@Table(name = "avisos")
public class Aviso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Cartao cartao;

	@NotBlank
	private String destino;

	@Future
	private LocalDate dataTermino;

	private LocalDateTime instanteAviso;

	private String ip;

	private String userAgent;

	@Deprecated
	public Aviso() {
	}

	public Aviso(@NotNull Cartao cartao, @NotBlank String destino, @Future LocalDate dataTermino, String ip,
			String userAgent) {
		this.cartao = cartao;
		this.destino = destino;
		this.dataTermino = dataTermino;
		this.instanteAviso = LocalDateTime.now();
		this.ip = ip;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public LocalDateTime getInstanteAviso() {
		return instanteAviso;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	@Override
	public String toString() {
		return "Aviso [cartao=" + cartao + ", destino=" + destino + ", dataTermino=" + dataTermino + ", instanteAviso="
				+ instanteAviso + ", ip=" + ip + ", userAgent=" + userAgent + "]";
	}

}
