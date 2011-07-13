package controlador;

import java.awt.Point;

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
		if(e.getActionCommand().equals("Nuevo")){
			System.out.println("Selecciono Nuevo");			
		}else{
			if(e.getActionCommand().equals("Abrir")){
				System.out.println("Selecciono Abrir");
			}else{
				if(e.getActionCommand().equals("Guardar")){
					System.out.println("Selecciono Guardar");
				}else{
					if(e.getActionCommand().equals("Salir")){
						System.exit(0);
					}else{
						if(e.getActionCommand().equals("Acerca de")){
							JOptionPane.showOptionDialog(vista, "Info","Acerca de DiagramasT", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},null);
						}
					}
					
				}
			}
		}		
	}
	
	public void eVmousePressed(MouseEvent ev) {
		if(SwingUtilities.isLeftMouseButton(ev)){ 			//Click boton izquierdo selecciona figura
			seleccionada=this.getFiguraEn(ev.getPoint());
		}else if(SwingUtilities.isRightMouseButton(ev)){		//click boton derecho añade figura tipo cuadrado
			this.anyadirFigura(new Cuadrado(ev.getPoint(),40));			
		}else if(SwingUtilities.isMiddleMouseButton(ev))//click boton medio añade figura tipo circulo
		{
			this.anyadirFigura(new Circulo(ev.getPoint(),40));
		}
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
