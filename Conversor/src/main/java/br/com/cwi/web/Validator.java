package br.com.cwi.web;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Validator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String feriadoAnoNovo = "0101";
	private final String feriadoTiradentes = "2104";
	private final String feriadoDiaTrabalho = "0105";
	private final String feriadoIndependência = "0709";
	private final String feriadoPadroeira = "0211";
	private final String feriadoProclamaRepublica = "1511";
	private final String feriadoNatal = "2512";

	private boolean alterouData = false;

	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
	
	public boolean validarCotacao(Cotacao cotacao) throws ParseException  {
		boolean retorno = true;
		if(cotacao.getData() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Data invalida!"));
			retorno = false;
		} else if (cotacao.getData().before(f.parse("19000101"))) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Data invalida!"));
			retorno = false;
		} 
		if(cotacao.getValorEntrada().doubleValue()<=0) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Valor a converter deve ser maior que 0(ZERO)!"));
			retorno = false;
		}
		if (cotacao.getMoedaEntrada().getCodigoMoeda().equals(cotacao.getMoedaSaida().getCodigoMoeda())) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("As moedas devem ser diferentes!"));
			retorno = false;
		}
		return retorno;
	}
	
	public Cotacao validarData(Cotacao cotacao){
		
		SimpleDateFormat sd = new SimpleDateFormat("ddMM");
		
		String data = sd.format(cotacao.getData());
		
		if ((data.equals(feriadoAnoNovo) || data.equals(feriadoTiradentes)
				|| data.equals(feriadoDiaTrabalho)
				|| data.equals(feriadoIndependência)
				|| data.equals(feriadoPadroeira)
				|| data.equals(feriadoProclamaRepublica) || data
					.equals(feriadoNatal))
				|| (cotacao.getData().getDay() == 0 || cotacao.getData()
						.getDay() == 6)) {
			cotacao.setData(subtairDia(cotacao.getData()));
			this.setAlterouData(true);
			return this.validarData(cotacao);

		}
		
		if(alterouData) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Não foi encontrda cotação para a data informada. Foi ultilizada uma data anterior a informada."));
		}
		
		return cotacao;
	}
	
	
	public Date subtairDia(Date data) {
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public boolean validarTaxas(Cotacao cotacao) {
		if (cotacao.getMoedaEntrada().getTaxaCompra() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Não foi encontrda taxa na data informada para a moeda: " + cotacao.getMoedaEntrada().getCodigoMoeda()));
			return false;
		}
		if (cotacao.getMoedaSaida().getTaxaCompra() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage("Não foi encontrda taxa na data informada para a moeda: " + cotacao.getMoedaSaida().getCodigoMoeda()));
			return false;
		}
		return true;
	}
	
	public boolean isAlterouData() {
		return alterouData;
	}

	public void setAlterouData(boolean alterouData) {
		this.alterouData = alterouData;
	}
	
	
}

