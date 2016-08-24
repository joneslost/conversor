package br.com.cwi.web.beans;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.cwi.web.Arquivo;
import br.com.cwi.web.Converte;
import br.com.cwi.web.Cotacao;
import br.com.cwi.web.Moeda;
import br.com.cwi.web.Moedas;
import br.com.cwi.web.Validator;

@ManagedBean(name = "conversorBean")
@SessionScoped
public class Conversor implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
	private Cotacao cotacao;
	private Validator validator;


	public Moedas[] getMoedasEntrada() {
		return Moedas.values();
	}
	
	public Moedas[] getMoedasSaida() {
		return Moedas.values();
	}

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}
	
	
	
	public String novaCotacao(){
		
		cotacao = new Cotacao();
		cotacao.setMoedaEntrada(new Moeda());
		cotacao.setMoedaSaida(new Moeda());
		
		validator = new Validator();
		
		return "cotar";
	}
	
	public String cotar() {
		
		
		try {
			
			if (validator.validarCotacao(cotacao)) {			
				
				validator.validarData(cotacao);
				
				String data = f.format(cotacao.getData());
				File file =  Arquivo.buscarArquivo(data);
				
				if (file == null) {
					FacesContext.getCurrentInstance().addMessage(null,
			                new FacesMessage("Não foi encontrada cotação para da data informada!"));
					return "";
				}
				
				
				Converte c = new Converte();
				c.encontarValor(file, cotacao);
				
				if(!validator.validarTaxas(cotacao)) {
					return "";
				}
				
				String path = file.getPath();
				file = null;
				Arquivo.removerArquivo(path);
				
				if(cotacao.getMoedaEntrada().getTaxaCompra() != null && cotacao.getMoedaSaida().getTaxaCompra() != null) {
					cotacao.setValorSainda((cotacao.getMoedaEntrada().getTaxaCompra() * cotacao.getValorEntrada()) / cotacao.getMoedaSaida().getTaxaCompra());
				}
				
				
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
}
