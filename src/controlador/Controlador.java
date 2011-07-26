package controlador;

import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import vista.Vista;
import modelo.Interprete;
import modelo.Programa;
import modelo.Maquina;
import modelo.Compilador;
import modelo.Figura;
import modelo.Modelo;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private Figura seleccionada;

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		seleccionada = null;
	}

	public Figura obtenerFigura(Point posicion) {
		ListIterator<Figura> it = modelo.getListado().listIterator();
		while (it.hasNext()) {
			Figura tmp = it.next();
			if (tmp.dentroFigura(posicion)) {
				tmp.setSeleccionada(true);
				return tmp;
			}
		}
		return null;
	}

	public void cambiarPosicion(Figura f, Point p) {
		f.setPosicion(p);
	}

	public Vista getVista() {
		return vista;
	}

	public void anyadirFigura(Figura f) {
		modelo.anyadirFigura(f);
	}

	public void eliminarFigura(Figura f) {
		modelo.eliminarFigura(f);
	}

	public Figura getFiguraEn(Point p) {
		return modelo.getFiguraEn(p);
	}

	public Figura getFiguraSa(Point p) {
		return modelo.getFiguraSa(p);
	}

	public void evMenuPressed(ActionEvent e) {
		stringEvent actionCommand = new stringEvent(e.getActionCommand());

		switch (actionCommand.getIntEvent()) {
		case stringEvent.NUEVO:
			String name = "";
			name = (String) JOptionPane.showInputDialog(vista,
					"Nombre del Compilador", "Nuevo Compilador",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
							"img/Nuevo.png"), null, null);
			if (name != null) {
				if (name.length() != 0) {
					vista.activeMouseEvent();
					vista.activarEscuchadorKey();
					vista.setNameProyect(name);
					JOptionPane.showMessageDialog(vista,
							"Para añadir componentes haga\n"
									+ "click derecho sobre el panel", "Tips",
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
									"img/info.png"));
				} else {
					JOptionPane.showOptionDialog(vista,
							"Debe ingresar un nombre valido", "Ouch!",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, new ImageIcon(
									"img/error.png"), new Object[] {}, null);
				}
			}else{
				System.out.println("Presiono Cancelar");
			}
			break;
		case stringEvent.ABRIR:
			break;
		case stringEvent.GUARDAR:
			break;
		case stringEvent.SALIR:
			System.exit(0);
			break;
		case stringEvent.ATAJOS:
			JOptionPane.showOptionDialog(vista, "   Nuevo         Ctrl + N\n"
					+ "   Abrir            Ctrl + A\n"
					+ "   Guardar      Ctrl + G\n"
					+ "   Salir             Ctrl + S\n"
					+ "   Acerca de   Ctrl + D\n\n"
					+ "   Agregar Compilador    Ctrl + C\n"
					+ "   Agregar Maquina         Ctrl + M\n"
					+ "   Agregar Programa      Ctrl + P\n"
					+ "   Agregar Interprete       Ctrl + I"
					+ "   Eliminar Compilador       Ctrl + A"
					+ "   Eliminar Maquina       Ctrl + B"
					+ "   Eliminar Programa       Ctrl + D"
					+ "   Eliminar Interprete       Ctrl + E",
					"Atajos de teclado", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
							"img/info.png"), new Object[] {}, null);
			break;
		case stringEvent.ACERCA_DE:
			JOptionPane.showOptionDialog(vista,
					"Aplicacion interactiva que permite disenar\n"
							+ "compiladores mediante Diagramas de Tombstone\n"
							+ "o tambien llamados Diagramas T",
					"Acerca de DiagramasT", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
							"img/info.png"), new Object[] {}, null);
			break;
		case stringEvent.AGREGAR_COMPILADOR:
			addCompilador();
			break;
		case stringEvent.AGREGAR_MAQUINA:
			addMaquina();
			break;
		case stringEvent.AGREGAR_PROGRAMA:
			addPrograma();			
			break;
		case stringEvent.AGREGAR_INTERPRETE:
			addInterprete();

			break;
		/*
		 * case stringEvent.ELIMINAR_COMPILADOR: int a = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getX() -
		 * vista.getParent().getLocationOnScreen().getX()); int b = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getY() -
		 * vista.getParent().getLocationOnScreen().getY());
		 * this.anyadirFigura(new Compilador(new
		 * Point(a,b),30,origen,destino,escrito)); break; case
		 * stringEvent.ELIMINAR_MAQUINA: int c = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getX() -
		 * vista.getParent().getLocationOnScreen().getX()); int d = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getY() -
		 * vista.getParent().getLocationOnScreen().getY());
		 * this.anyadirFigura(new Maquina(new Point(c,d),30,maquina)); break;
		 * case stringEvent.ELIMINAR_PROGRAMA: int f = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getX() -
		 * vista.getParent().getLocationOnScreen().getX()); int g = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getY() -
		 * vista.getParent().getLocationOnScreen().getY());
		 * this.anyadirFigura(new Programa(new
		 * Point(f,g),30,programa,lenguaje)); break; case
		 * stringEvent.ELIMINAR_INTERPRETE: int h = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getX() -
		 * vista.getParent().getLocationOnScreen().getX()); int i = (int)
		 * ((int)MouseInfo.getPointerInfo().getLocation().getY() -
		 * vista.getParent().getLocationOnScreen().getY());
		 * this.anyadirFigura(new Interprete(new
		 * Point(h,i),30,interprete,escrito1)); break;
		 */
		}
		vista.repaint();
	}

	public void eVmousePressed(MouseEvent ev) {
		seleccionada = this.getFiguraEn(ev.getPoint());

		vista.repaint();
	}

	public void eVmouseDragged(MouseEvent ev) {
		if (seleccionada != null) {
			// El movimiento puede ser mas fluido recalculando el pto
			this.cambiarPosicion(seleccionada, ev.getPoint());
			vista.repaint();
		}
	}

	public void eVmouseReleased(MouseEvent ev) {
		vista.repaint();
		if (seleccionada != null) {
			seleccionada.setSeleccionada(false);
			seleccionada = null;
		}

	}
	
	  public keyController activarEscuchadorKey() {
	        return new keyController(this);
	    }
	  
	  /**
	     * Metodo que agrega un compiladora a la vista
	     * @return true si todo fue correcto
	     */
	  public boolean addCompilador(){
		  String origen = (String) JOptionPane.showInputDialog(vista, "Nombre del lenguaje fuente :", "Compilador", JOptionPane.INFORMATION_MESSAGE,
	                null, null, null);
	        if (origen != null) {
	            String destino = (String) JOptionPane.showInputDialog(vista, "Nombre del lenguaje objeto :", "Compilador", JOptionPane.INFORMATION_MESSAGE,
	                    null, null, null);
	            if (destino != null) {
	                String escrito = (String) JOptionPane.showInputDialog(vista, "Escrito en :", "Nuevo Compilador", JOptionPane.INFORMATION_MESSAGE,
	                        null, null, null);
	                if (escrito != null) {

			int a = (int) ((int) MouseInfo.getPointerInfo().getLocation()
					.getX() - vista.getParent().getLocationOnScreen().getX());
			int b = (int) ((int) MouseInfo.getPointerInfo().getLocation()
					.getY() - vista.getParent().getLocationOnScreen().getY());
			this.anyadirFigura(new Compilador(new Point(a, b), 40,40,origen,
					destino, escrito));

	                } else {
	                    return false;
	                }
	            } else {
	                return false;
	            }
	        } else {
	            return false;
	        }

	        return true;
	  }
	  
	  /**
	     *
	     * @return
	     */
	    public boolean addMaquina() {
	        String maquina = (String) JOptionPane.showInputDialog(vista, "Nombre de la plataforma de ejecucion :", "Maquina", JOptionPane.INFORMATION_MESSAGE,
	                null, null, null);
	        if (maquina == null) {
	            return false;
	        }
	        int c = (int) ((int) MouseInfo.getPointerInfo().getLocation()
					.getX() - vista.getParent().getLocationOnScreen().getX());
			int d = (int) ((int) MouseInfo.getPointerInfo().getLocation()
					.getY() - vista.getParent().getLocationOnScreen().getY());
	        this.anyadirFigura(new Maquina(new Point(c, d), 40,40, maquina));
	        return true;
	    }

	    /**
	     *
	     * @return
	     */
	    public boolean addPrograma() {

	        String programa = (String) JOptionPane.showInputDialog(vista, "Nombre del programa :", "Programa", JOptionPane.INFORMATION_MESSAGE,
	                null, null, null);
	        if (programa != null) {
	            String lenguaje = (String) JOptionPane.showInputDialog(vista, "Escrito en el lenguaje :", "Programa", JOptionPane.INFORMATION_MESSAGE,
	                    null, null, null);
	            if (lenguaje == null) {
	                return false;
	            }
	            /***TODO BIEN***/
	            int f = (int) ((int) MouseInfo.getPointerInfo().getLocation()
						.getX() - vista.getParent().getLocationOnScreen().getX());
				int g = (int) ((int) MouseInfo.getPointerInfo().getLocation()
						.getY() - vista.getParent().getLocationOnScreen().getY());
				this.anyadirFigura(new Programa(new Point(f, g), 40,40, programa,
						lenguaje));
	        } else {
	            return false;
	        }

	        
	        return true;
	    }

	    /**
	     *
	     * @return
	     */
	    public boolean addInterprete() {

	        String interprete = (String) JOptionPane.showInputDialog(vista, "Interprete del lenguaje :", "Interprete", JOptionPane.INFORMATION_MESSAGE,
	                null, null, null);
	        if (interprete != null) {
	            String escrito1 = (String) JOptionPane.showInputDialog(vista, "Escrito en :", "Interprete", JOptionPane.INFORMATION_MESSAGE,
	                    null, null, null);
	            if (escrito1 == null) {
	                return false;
	            }

	            int h = (int) ((int) MouseInfo.getPointerInfo().getLocation()
						.getX() - vista.getParent().getLocationOnScreen().getX());
				int i = (int) ((int) MouseInfo.getPointerInfo().getLocation()
						.getY() - vista.getParent().getLocationOnScreen().getY());
				this.anyadirFigura(new Interprete(new Point(h, i), 40,40, interprete,
						escrito1));
	        } else {
	            return false;
	        }



	        return true;
	    }
}





class keyController implements KeyListener {

    Controlador controlador;
    private final Set<Integer> pressed = new HashSet<Integer>();

    keyController(Controlador aThis) {
        this.controlador = aThis;
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }
/**
 * 
 * @param ke
 */
    @Override
    public void keyPressed(KeyEvent ke) {


        pressed.add(ke.getKeyCode());
        if (pressed.size() == 2) {

            if (pressed.contains(17)) {
                if (pressed.contains(67)) {
                    System.out.println("Preciono C");
                    this.controlador.addCompilador();
                    this.controlador.getVista().repaint();
                    this.removerTeclas();
                } else if (pressed.contains(77)) {
                    System.out.println("Preciono M");
                    this.controlador.addMaquina();
                    this.controlador.getVista().repaint();
                    this.removerTeclas();
                } else if (pressed.contains(80)) {
                    System.out.println("Preciono P");
                    this.controlador.addPrograma();
                    this.controlador.getVista().repaint();
                    this.removerTeclas();
                } else if (pressed.contains(73)) {
                    System.out.println("Preciono I");
                    this.controlador.addInterprete();
                    this.controlador.getVista().repaint();
                    this.removerTeclas();
                } else if (pressed.contains(65)) {
                    System.out.println("Preciono A");
                } else if (pressed.contains(66)) {
                    System.out.println("Preciono B");
                } else if (pressed.contains(68)) {
                    System.out.println("Preciono D");
                    pressed.remove(68);
                    pressed.remove(17);
                } else if (pressed.contains(69)) {
                    System.out.println("Preciono E");
                } else if (pressed.contains(78)) {
                    System.out.println("Preciono N");
                    pressed.remove(78);
                    pressed.remove(17);
                }

            }

            // More than one key is currently pressed.
            // Iterate over pressed to get the keys.
        }


    }

    public void removerTeclas() {
        pressed.clear();
    }
    @Override
    public void keyReleased(KeyEvent ke) {

        pressed.remove(ke.getKeyCode());
    }

	
}

