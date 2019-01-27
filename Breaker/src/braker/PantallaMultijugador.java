package braker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PantallaMultijugador extends JPanel implements KeyListener, ActionListener {

	
	/*
	 * Atributos de la clase Multijugador
	 */
	private boolean play = false;
	private int marcadorJ1 = 0;
	private int marcadorJ2 = 0;
	private int totalPaneles = 800;
	private Timer tiempo;
	private int retrasar = 8;
	private int jugador1 = 350;
	private int jugador2 = 600;
	private int pelotaPosX = 1000;
	private int pelotaPosY = 1000;
	private int pelotaDireccionX = -5;
	private int pelotaDireccionY = -7;
	private Mapa mapa;
	private boolean pause = false;
	private int posActualX;
	private int posActualY;

	public static int seleccionNivel() {
		/*
		 * Seleccion de la dificultad 
		 */
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
				new Object[] { "Imposible", "Dificil", "Medio", "Facil" }, 
				"opcion 1");
		if (seleccion != -1)
			System.out.println("seleccionada opcion " + (seleccion + 1));
		return seleccion + 1;
	}

	public void nivel() {
		switch (seleccionNivel()) {
		/*
		 * Posicionamiento de los objetos 
		 * en la pantalla
		 */
		case 4:
			play = true;
			pelotaPosX = 500;
			pelotaPosY =950;
			pelotaDireccionX = +3;
			pelotaDireccionY = +4;
			jugador1 = 500;
			jugador2 = 500;
			marcadorJ1 = 0;
			marcadorJ2 = 0;
			totalPaneles = 21;
			mapa = new Mapa(2, 7);
			repaint();
			break;
		case 3:
			play = true;
			pelotaPosX = 120;
			pelotaPosY = 350;
			pelotaDireccionX = -4;
			pelotaDireccionY = -5;
			jugador1 = 310;
			//marcador = 0;
			totalPaneles = 40;
			mapa = new Mapa(5, 8);
			repaint();
			break;

		case 2:
			play = true;
			pelotaPosX = 120;
			pelotaPosY = 350;
			pelotaDireccionX = -7;
			pelotaDireccionY = -8;
			jugador1 = 310;
			//marcador = 0;
			totalPaneles = 104;
			mapa = new Mapa(8, 12);
			repaint();
			break;

		case 1:
			play = true;
			pelotaPosX = 120;
			pelotaPosY = 350;
			pelotaDireccionX = -10;
			pelotaDireccionY = -12;
			jugador1 = 310;
			//marcador = 0;
			totalPaneles = 150;
			mapa = new Mapa(10, 15);
			repaint();
			break;

		default:
			break;
		}
	}
	
	

	public PantallaMultijugador() {
		nivel();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tiempo = new Timer(retrasar, this);
		tiempo.start();
		// ((Object) tiempo).schedule( 10, 1000);
	}

	public void paint(Graphics g) {
		// Fondo
		// sobre 1190 y 1020
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 1182, 1012);

		// Panel
		mapa.dibujar1vs1((Graphics2D) g, 2, 7);

		// Bordes
		g.setColor(Color.RED);
		g.fillRect(0, 0, 5, 1012);
		g.fillRect(0, 0, 1182, 0);
		g.fillRect(1182, 0, 5, 1012);

		// Marcador
		g.setColor(Color.blue);
		g.setFont(new Font("Courier", Font.BOLD, 25));
		g.drawString("Player 2: " + marcadorJ2, 980, 50);
		
		// Marcador J2
		g.setColor(Color.green);
		g.setFont(new Font("Courier", Font.BOLD, 25));
		g.drawString("Player 1: " + marcadorJ1, 30, 50);

		// Colores Jugador 1
		g.setColor(Color.green);
		g.fillRect(jugador1, 970, 170, 8);
		
	    // Colores Jugador 2
		g.setColor(Color.blue);
		g.fillRect(jugador2, 20, 170, 8);
		
		

		// Bola
		g.setColor(Color.yellow);
		g.fillOval(pelotaPosX, pelotaPosY, 20, 20);

		// Has ganado
		if (totalPaneles <= 0) {
			play = false;
			pelotaDireccionX = 0;
			pelotaDireccionY = 0;
			g.setColor(Color.green);
			g.setFont(new Font("Courier", Font.BOLD, 55));
			g.drawString("GANA JUGADOR 1", 35, 400);

			g.setColor(Color.green);
			g.setFont(new Font("Courier", Font.BOLD, 55));
			g.drawString("Presiona intro para volver a jugar", 35, 440);
			boolean perder = true;

		}
		// Has Perdido J1
		if (marcadorJ2 >= 3) {
			play = false;
			pelotaDireccionX = 0;
			pelotaDireccionY = 0;
			g.setColor(Color.blue);
			g.setFont(new Font("Courier", Font.BOLD, 55));
			g.drawString("GANA JUGADOR 2", 400, 400);

			g.setColor(Color.blue);
			g.setFont(new Font("Courier", Font.BOLD, 55));
			g.drawString("Presiona intro para volver a jugar", 35, 440);
			
			
		}
		// Has PerdidoJ2
		if (marcadorJ1 >= 3) {
			play = false;
			pelotaDireccionX = 0;
			pelotaDireccionY = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Courier", Font.BOLD, 55));
			g.drawString("GANA JUGADOR 1", 400, 400);
			
			g.setFont(new Font("Courier", Font.BOLD, 55));
			g.drawString("Presiona intro para volver a jugar", 35, 440);
			
			
		}
		if (pause) {
				g.setFont(new Font("Courier", Font.BOLD, 55));
				g.drawString("PAUSE ", 500, 400);
			}
	}

	/*
	 * Creacion de los paneles y condiciones 
	 * de destruccion de los mismos
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		tiempo.start();

		if (play) {

			if (new Rectangle(pelotaPosX, pelotaPosY, 20, 20).intersects(new Rectangle(jugador1, 970, 170, 8))||new Rectangle(pelotaPosX, pelotaPosY, 20, 20).intersects(new Rectangle(jugador2, 20, 170, 8))) {
				pelotaDireccionY = -pelotaDireccionY;
			}

			A: for (int i = 0; i < mapa.mapa.length; i++) {
				for (int j = 0; j < mapa.mapa[0].length; j++) {
					if (mapa.mapa[i][j] > 0) {
						int panelX = j * mapa.anchoPanel + 260;
						int panelY = i * mapa.altoPanel + 400;
						int anchoPanel = mapa.anchoPanel;
						int altoPanel = mapa.altoPanel;

						Rectangle rect = new Rectangle(panelX, panelY, anchoPanel, altoPanel);
						Rectangle pelotaRect = new Rectangle(pelotaPosX, pelotaPosY, 20, 20);
						Rectangle panelRect = rect;

						if (pelotaPosY < 0) {
							mapa.puntos(0, i, j);
							marcadorJ1 ++;
							
							pelotaPosX = 500;
							pelotaPosY =950;
							pelotaDireccionX = -3;
							pelotaDireccionY = -4;
							
							}else if (pelotaPosY > 1000) {
							mapa.puntos(0, i, j);
								marcadorJ2 ++;
								pelotaPosX = 500;
								pelotaPosY =50;
								pelotaDireccionX = +3;
								pelotaDireccionY = +4;
								
							}
						
						if (pelotaRect.intersects(panelRect)) {
							mapa.puntos(0, i, j);
							totalPaneles--;
							//marcador += 5;
							

							if (pelotaPosX + 19 <= panelRect.x || pelotaPosX + 1 >= panelRect.x + panelRect.width) {
								pelotaDireccionX = -pelotaDireccionX;
							} else {
								pelotaDireccionY = -pelotaDireccionY;
							}
							
							break A;
						}
					}
				}
			}

			pelotaPosX += pelotaDireccionX;
			pelotaPosY += pelotaDireccionY;
			if (pelotaPosX < 0) {
				pelotaDireccionX = -pelotaDireccionX;
			}
//			if (pelotaPosY < 0) {
//				pelotaDireccionY = -pelotaDireccionY;
//			}
			if (pelotaPosX > 1160) {
				pelotaDireccionX = -pelotaDireccionX;
			}

		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		/*
		 * Eventos de las diferentes teclas
		 */

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (jugador1 >= 1010) {
				jugador1 = 1010;
			} else if(play) {
				moverDerJ1();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (jugador1 <= 10) {
				jugador1 = 10;
			} else if (play){
				moverIzqJ1();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (jugador2 >= 1010) {
				jugador2 = 1010;
			} else if(play) {
				moverDerJ2();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (jugador2 <= 10) {
				jugador2 = 10;
			} else if (play){
				moverIzqJ2();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				nivel();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (play) {
				
				pause = true;
				posActualX = pelotaDireccionX;
				posActualY = pelotaDireccionY;
				
				pelotaDireccionX = 0;
				pelotaDireccionY = 0;
				play = false;
			}else if (!play){
				play = true;
				pause = false;
				pelotaDireccionX = posActualX;
				pelotaDireccionY = posActualY;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			if (play) {
				play=false;
				nivel();
			}
		}
		
	}
	
	/*
	 * Movimientos de los jugadores 
	 */

	public void moverIzqJ1() {
		//boolean movimiento = true;
		play = true;
		jugador1 -= 70;
	}

	public void moverDerJ1() {
		//boolean movimiento = true;
		play = true;
		jugador1 += 70;
	}
	public void moverIzqJ2() {
		//boolean movimiento = true;
		play = true;
		jugador2 -= 70;
	}
	
	public void moverDerJ2() {
		//boolean movimiento = true;
		play = true;
		jugador2 += 70;
	}

}
