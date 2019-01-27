package braker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mapa {

	public int mapa[][];
	public int anchoPanel;
	public int altoPanel;
	boolean multijugador = false;
	
	public Mapa(int fila, int col) {
		mapa = new int[fila][col];
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				mapa[i][j] = 1;
			}
		}
		
		anchoPanel = 980/col;
		altoPanel = 278/fila;
	}
	
	
	public void dibujar(Graphics2D g) {
		
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if (mapa[i][j] > 0) {
					g.setColor(Color.WHITE);
					g.fillRect(j * anchoPanel + 110,i * altoPanel +80,anchoPanel, altoPanel);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * anchoPanel + 110,i * altoPanel +80,anchoPanel, altoPanel);
				}
			}
		}
	}
	
	public void dibujar1vs1(Graphics2D g, int fila, int col) {
		anchoPanel = 700/col;
		altoPanel = 150/fila;
		
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[0].length; j++) {
				if (mapa[i][j] > 0) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * anchoPanel + 260,i * altoPanel +400,anchoPanel, altoPanel);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * anchoPanel + 260,i * altoPanel +400,anchoPanel, altoPanel);
				}
			}
		}
	}
	
	public void puntos(int valor, int fila , int col ) {
		mapa[fila][col] = valor;
	}
}
