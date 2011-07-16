package controlador;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

import vista.Vista;
import modelo.Circulo;
import modelo.Cuadrado;
import modelo.Figura;
import modelo.Modelo;


public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private Figura seleccionada;
	
	public Controlador(Modelo modelo, Vista vista){
		this.modelo=modelo;
		this.vista=vista;
		seleccionada=null;
	}
	
	public Figura obtenerFigura(Point posicion){
		ListIterator<Figura> it=modelo.getListado().listIterator();
	    while (it.hasNext()) {
	    	Figura tmp=it.next();
	    		if(tmp.dentroFigura(posicion)){
	    			tmp.setSeleccionada(true);
	    			return tmp;
	    		}
		    }
	    return null;
	}

	public void cambiarPosicion(Figura f, Point p){
		f.setPosicion(p);
	}
	
	public Vista getVista(){
		return vista;
	}
	
	public void anyadirFigura(Figura f){
		modelo.anyadirFigura(f);
	}
	
	public Figura getFiguraEn(Point p){
		return modelo.getFiguraEn(p);
	}
	
	public void evMenuPressed(ActionEvent e){
		stringEvent actionCommand = new stringEvent(e.getActionCommand());
		
		switch(actionCommand.getIntEvent()){
			case stringEvent.NUEVO:
				String name = (String)JOptionPane.showInputDialog(vista,"Nombre del Compilador","Nuevo Compilador",JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("img/Nuevo.png"),null, null);
				if(name.length()!=0){
					vista.activeMouseEvent();
					vista.setNameProyect(name);
					JOptionPane.showOptionDialog(vista, "Para a�adir componentes haga\n" +
														"click derecho sobre el panel",
							"Tips", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("img/info.png"), new Object[] {},null);
				}else{
					JOptionPane.showOptionDialog(vista, "Debe ingresar un nombre v�lido",
				"Ouch!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,
				new ImageIcon("img/error.png"), new Object[] {},null);
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
				JOptionPane.showOptionDialog(vista, "   Nuevo         Ctrl + N\n" +
													"   Abrir            Ctrl + A\n" +
													"   Guardar      Ctrl + G\n" +
													"   Salir             Ctrl + S\n" +
													"   Acerca de   Ctrl + D\n\n" +													
													"   Agregar Compilador    Ctrl + C\n" +
													"   Agregar M�quina         Ctrl + M\n" +
													"   Agregar Programa      Ctrl + P\n" +
													"   Agregar Interprete       Ctrl + I",
										"Atajos de teclado", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
										new ImageIcon("img/info.png"), new Object[] {},null);
			break;
			case stringEvent.ACERCA_DE:
				JOptionPane.showOptionDialog(vista,"Aplicaci�n interactiva que permite dise�ar\n" +
													"compiladores mediante Diagramas de Tombstone\n" +
													"o tambi�n llamados Diagramas T","Acerca de DiagramasT", 
													JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, new ImageIcon("img/info.png"), new Object[] {},null);
			break;
			case stringEvent.AGREGAR_COMPILADOR:
				System.out.println("add compiler");
			break;
			case stringEvent.AGREGAR_MAQUINA:
				System.out.println("add machine");
			break;
			case stringEvent.AGREGAR_PROGRAMA:
				System.out.println("add program");
			break;
			case stringEvent.AGREGAR_INTERPRETE:
				System.out.println("add interprete");
			break;			
		}
	}
	
	public void eVmousePressed(MouseEvent ev) {
		if(SwingUtilities.isLeftMouseButton(ev)){ 			//Click boton izquierdo selecciona figura
			seleccionada=this.getFiguraEn(ev.getPoint());
		}/*else if(SwingUtilities.isRightMouseButton(ev)){		//click boton derecho a�ade figura tipo cuadrado
			this.anyadirFigura(new Cuadrado(ev.getPoint(),40));			
		}else if(SwingUtilities.isMiddleMouseButton(ev))//click boton medio a�ade figura tipo circulo
		{
			this.anyadirFigura(new Circulo(ev.getPoint(),40));
		}*/
		vista.repaint();		
	}
	
	public void eVmouseDragged(MouseEvent ev) {
		if(seleccionada!=null){
			//El movimiento puede ser mas fluido recalculando el pto
			this.cambiarPosicion(seleccionada, ev.getPoint());
			vista.repaint();
		}
	}

	public void eVmouseReleased (MouseEvent ev) {
		vista.repaint();
		if(seleccionada!=null){
			seleccionada.setSeleccionada(false);
			seleccionada=null;
		}
	}

}