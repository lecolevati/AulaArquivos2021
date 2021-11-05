package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	@Override
	public void leDir(String path) throws IOException {
		File dir = new File(path); //Abriu o diretorio de caminho path
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				String fileName = "";
				if (f.isDirectory()) {
					fileName = "<DIR> ";
				} else {
					fileName = "      ";
				}
				fileName = fileName + f.getName();
				System.out.println(fileName);
			}
		} else {
			throw new IOException("Diretório inválido");
		}
	}

	private String geraConteudo() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while (!linha.equals("fim")) {
			linha = JOptionPane.showInputDialog(null, 
					"Digite uma frase", "ENTRADA",
					JOptionPane.PLAIN_MESSAGE);
			if (!linha.equals("fim")) {
				buffer.append(linha + "\r\n");
			}
		}
		return buffer.toString();
	}
	
	@Override
	public void criaArquivo(String path, String name) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File arq = new File(path, name);
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			
			String conteudo = geraConteudo();
			
			FileWriter abreArq = new FileWriter(arq, existe);
			PrintWriter escreve = new PrintWriter(abreArq);
			escreve.write(conteudo);
			escreve.flush();
			escreve.close();
			abreArq.close();
			
		} else {
			throw new IOException("Diretório inválido");
		}
	}

	@Override
	public void abreArquivo(String path, String name) throws IOException {
		File arq = new File(path, name);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leFluxo);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				System.out.print(vetLinha[0] + "\t");
				System.out.print(vetLinha[1] + "\t\t");
				System.out.println(vetLinha[2]);
				
				linha = buffer.readLine();
			}
			buffer.close();
			leFluxo.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}
	}

	@Override
	public void chamaArquivo(String path, String name) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		File arq = new File(path, name);
		if (arq.exists() && arq.isFile()) {
			desktop.open(arq);
		} else {
			throw new IOException("Arquivo inválido");
		}
	}

}
