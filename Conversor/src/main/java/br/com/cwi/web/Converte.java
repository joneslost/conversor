package br.com.cwi.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Converte {
	
	BufferedReader br = null;
	String linha = "";
	String separador = ";";
	
	public Cotacao encontarValor(File file, Cotacao cotacao) {
		
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			while ((linha = br.readLine()) != null) {

	            String[] pais = linha.split(separador);
	            
	            if (cotacao.getMoedaEntrada().getCodigoMoeda().equals(pais[3])) {
	            	cotacao.getMoedaEntrada().setTaxaCompra(new Double(pais[4].replace(",", ".")));
	            }
	            if (cotacao.getMoedaSaida().getCodigoMoeda().equals(pais[3])) {
	            	cotacao.getMoedaSaida().setTaxaCompra(new Double(pais[4].replace(",", ".")));
	            }

	        }
			 
		} catch (IOException e) {
		}
		return cotacao;
	}
}
