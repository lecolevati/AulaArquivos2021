package controller;

import java.io.IOException;

public interface IArquivosController {

	public void leDir(String path) throws IOException;	
	public void criaArquivo(String path, String name) throws IOException;
	public void abreArquivo(String path, String name) throws IOException;
	public void chamaArquivo(String path, String name) throws IOException;
	
}
