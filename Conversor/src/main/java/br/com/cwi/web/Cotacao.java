package br.com.cwi.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class Cotacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Moeda moedaEntrada;
	private Moeda moedaSaida;
	private Date data;
	private Double valorEntrada;
	private Double valorSainda;
	
	
	public Moeda getMoedaEntrada() {
		return moedaEntrada;
	}
	public void setMoedaEntrada(Moeda moedaEntrada) {
		this.moedaEntrada = moedaEntrada;
	}
	public Moeda getMoedaSaida() {
		return moedaSaida;
	}
	public void setMoedaSaida(Moeda moedaSaida) {
		this.moedaSaida = moedaSaida;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public Double getValorSainda() {
		return valorSainda;
	}
	public void setValorSainda(Double valorSainda) {
		this.valorSainda = valorSainda;
	}

	
}

