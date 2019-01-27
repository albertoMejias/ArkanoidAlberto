package braker;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Principal {

	
	private static boolean jugadores = false;

	public static void main(String[] args) {
		JFrame ventana = new JFrame();

		// Dependiendo del la opcion seleccionada cargamos una pantalla u otra
		jugadores = jugadores();
		if (jugadores) {
			PantallaJuego pantallaJuego = new PantallaJuego();
			ventana.setBounds(250, 100, 1190, 1020);
			ventana.setTitle("Brick Breaker");
			ventana.setResizable(true);
			ventana.setVisible(true);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.add(pantallaJuego);
		}else {
			PantallaMultijugador pantallaMultijugador = new PantallaMultijugador();
			ventana.setBounds(250, 100, 1190, 1020);
			ventana.setTitle("Brick Breaker");
			ventana.setResizable(true);
			ventana.setVisible(true);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.add(pantallaMultijugador);
		}		
	}

	public static boolean jugadores() {
		int jugadores = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
				new Object[] {  "1 vs 1", "Arcade" }, 
				"opcion 1");
		if (jugadores+1 == 1) {
			return false;
		}
		return true;
	}
}