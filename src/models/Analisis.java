package models;

import java.text.DecimalFormat;
import java.util.Collection;

public class Analisis {
	private String codigo;
	private int lineasCodigo;
	private int lineasComentadas;
	private int lineasTotales;
	private int lineasBlancas;
	private int complejidad;
	private int fanIn;
	private int fanOut;
	private float longHalstead;
	private float volHalstead;
	private float esfHalstead;
	
	public Analisis(String codigo,String archivoSeleccionado,Collection<String> listaArchivos) {
		this.codigo = codigo;
		actualizarDatosAnalisis(listaArchivos,archivoSeleccionado);
	}

	private void actualizarDatosAnalisis( Collection<String> listaArchivos, String archivoSeleccionado){
		LineaCodigo lineaCodigo = new LineaCodigo(codigo);
		lineasCodigo = lineaCodigo.getLineasCodigo();
		lineasComentadas = lineaCodigo.getLineasComentadas();
		lineasTotales = lineaCodigo.getLineas();
		lineasBlancas = lineaCodigo.getLineasBlancas();
		complejidad = ComplejidadCiclomatica.obtener(codigo);
		fanOut = FanInOut.getFanIn(codigo, listaArchivos); 
		fanIn = FanInOut.getFanOut(codigo,archivoSeleccionado, listaArchivos);
		
		Halstead h = new Halstead(codigo);
		longHalstead = h.getLongitud();
		volHalstead = h.getVolumen();
		esfHalstead = new Double(h.getEsfuerzo()).floatValue();
	}
	
	public int getLineasCodigo() {
		return lineasCodigo;
	}
	public int getLineasComentadas() {
		return lineasComentadas;
	}
	public int getLineasTotales() {
		return lineasTotales;
	}
	public int getLineasBlancas() {
		return lineasBlancas;
	}
	public String getPorcentajeLineasComentadas() {
		DecimalFormat df = new DecimalFormat("0.00");
		return String.valueOf(df.format(((float) lineasComentadas / lineasCodigo) * 100) + "%");
	}
	public int getComplejidad() {
		return complejidad;
	}
	public int getFanIn() {
		return fanIn;
	}
	public int getFanOut() {
		return fanOut;
	}
	public float getLongHalstead() {
		return longHalstead;
	}
	public float getVolHalstead() {
		return volHalstead;
	}
	public float getEsfHalstead() {
		return esfHalstead;
	}

}
