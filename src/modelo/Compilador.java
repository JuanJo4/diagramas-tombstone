package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Compilador extends Figura {

	private int ancho;
	private String origen;
    private String destino;
    private String escrito;
    
    public Compilador(Point posicion, int ancho, String a, String b, String c){
		this.posicion=posicion;
		this.ancho=ancho;
		this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
        this.origen=a;
        this.destino=b;
        this.escrito=c;
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
		g.setColor(Color.BLUE);
		
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAncho()); // cuadrado central
		g.fillRect(this.getX()-this.getAncho(), this.getY(), this.getAncho(), this.getAncho()); // cuadrado izquierdo
		g.fillRect(this.getX()+this.getAncho(), this.getY(), this.getAncho(), this.getAncho());
		g.fillRect(this.getX(), this.getY()+this.getAncho(), this.getAncho(), this.getAncho());
		
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);
		}
		
		g.setColor(Color.black);
        g.drawString(origen, getX()-20, getY()+18);
        g.setColor(Color.black);
        g.drawString(destino,getX()+40,getY()+18);
        g.setColor(Color.black);
        g.drawString(escrito,getX()+10,getY()+50);
		
	}	
}
