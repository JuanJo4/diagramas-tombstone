package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Maquina extends Figura {

	private int ancho;
	private int []xPoints = new int [3];
	private int []yPoints = new int [3];
	public Maquina(Point posicion, int ancho){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
	}
	
	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
	}
	
	@Override
	//Muy rudimentario y solo a modo demostrativo, para uso serio debe ser mejorada
	public boolean dentroFigura(Point p) {
		int difX=Math.abs(p.x-(posicion.x+(ancho/2)));
		int difY=Math.abs(p.y-(posicion.y+(ancho/2)));
		return ( (difX<ancho/2) && (difY<ancho/2));   
	}
	
	@Override
	public void dibujar(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAncho()); // cuadrado central

		xPoints[0] = this.getX();
		xPoints[1] = this.getX()+(this.getAncho()/2);
		xPoints[2] = this.getX()+this.getAncho();
		
		yPoints[0] = this.getY()+this.getAncho();
		yPoints[1] = this.getY()+(this.getAncho()+(this.getAncho()/2));
		yPoints[2] = this.getY()+this.getAncho();
		
		g.fillPolygon(xPoints, yPoints, 3);
		
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);
		}
	}

}
