package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class LineaCodigo {

	private String codigo;
	private int lineasComentadas;
	private int lineasSinComentar;
	private int lineas;
	private int lineasCodigo;
	private int lineasBlancas;

	public LineaCodigo(String codigo) {
		this.codigo = codigo;
		lineasComentadas = 0;
		lineasSinComentar = 0;
		lineas = 0;
		lineasCodigo = 0;
		lineasBlancas = 0;
		calcularLineas();
	}

	@SuppressWarnings("resource")
	private void calcularLineas() {

		String linea;
		Scanner sc = new Scanner(codigo);

		while(sc.hasNextLine()) {
			this.lineas++;
			linea = sc.nextLine();
			linea=linea.replace(" ","");
			if(linea.contains("//")) {
				String[] separador=linea.split("//");
//				if(!separador[1].trim().isEmpty()) { //ES UN COMENTARIO NO VACIO
//					this.lineasComentadas++;
//				}
				this.lineasComentadas++;
				if(!separador[0].trim().isEmpty()) { // LA LINEA TIENE CODIGO ADEMAS DE COMENTARIO
					this.lineasCodigo++;
				}
			}
			else if(linea.contains("/*")) {
				String[] separador = linea.split("/*");
				if(!separador[0].trim().isEmpty()) { // LA LINEA TIENE CODIGO ADEMAS DE COMENTARIO
					this.lineasCodigo++;
				}
				 if(linea.contains("*/")) {
					 this.lineasComentadas++;
					 separador = linea.split("*/");
					 
					 if(!separador[1].trim().isEmpty()) { // LA LINEA TIENE CODIGO ADEMAS DE COMENTARIO
						this.lineasCodigo++;
					}
				 }
				 else {
					 this.lineasComentadas++;
					 while(sc.hasNextLine()) {
						 this.lineas++;
						 this.lineasComentadas++;
						 linea = sc.nextLine();
						 if(linea.contains("*/")) {
							 break;
						 }
					 }
				 }
			}
			else if(linea.trim().isEmpty()) { // LINEA VACIA
				this.lineasBlancas++;
			}
			else { //ES UNA LINEA QUE SOLAMENTE TIENE CODIGO
				this.lineasCodigo++;
			}
		}

		sc.close();

	}

	public int getLineasComentadas() {
		return lineasComentadas;
	}

	public int getLineasSinComentar() {
		return lineasSinComentar;
	}
	
	public int getLineas() {
		return lineas;
	}

	public int getLineasCodigo() {
		return lineasCodigo;
	}

	public int getLineasBlancas() {
		return lineasBlancas;
	}
	
	
}
