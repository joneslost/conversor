package br.com.cwi.web;

import java.io.Serializable;

public class Moeda implements Serializable{
	
	private String codigoMoeda;
	private Double taxaCompra;
	
	
	public String getCodigoMoeda() {
		return codigoMoeda;
	}
	public void setCodigoMoeda(String codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}
	public Double getTaxaCompra() {
		return taxaCompra;
	}
	public void setTaxaCompra(Double taxaCompra) {
		this.taxaCompra = taxaCompra;
	}

	
	
}
