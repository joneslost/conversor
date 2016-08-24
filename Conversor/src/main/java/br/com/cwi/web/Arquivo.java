package br.com.cwi.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Arquivo {

	public static File buscarArquivo(String data) {
		
		String urlArquivo = "http://www4.bcb.gov.br/Download/fechamento/?.csv";
		
		urlArquivo = urlArquivo.replace("?", data);
		
		try {
			URL url = new URL(urlArquivo);
			InputStream is = url.openStream();
			String nomeArquivoLocal = data+".csv";
			FileOutputStream fos = new FileOutputStream("c://temp//moeda//"+nomeArquivoLocal);
			int umByte = 0;
			while ((umByte = is.read()) != -1){
				fos.write(umByte);
			}
			is.close();
			fos.close();
			return new File("c://temp//moeda//"+nomeArquivoLocal);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static boolean removerArquivo(String path) {
		File file = new File(path);
		boolean b = file.delete();
		return b;
	}
	
}
