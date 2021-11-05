package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		IArquivosController arqCont = new ArquivosController();
		try {
//			arqCont.leDir("C:\\");
//			arqCont.criaArquivo("C:\\TEMP", "catalogo.csv");
//			arqCont.abreArquivo("C:\\TEMP", "catalogo.csv");
			arqCont.chamaArquivo("C:\\TEMP", "SteamCharts.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
