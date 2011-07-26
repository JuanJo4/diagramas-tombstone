package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Compilador extends Figura {

	private int ancho;
	private int alto;
	private String origen;
    private String destino;
    private String escrito;
    
    public Compilador(Point posicion, int ancho,int alto, String a, String b, String c){
		this.posicion=posicion;
		this.ancho = ancho;
		this.alto = alto;
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
	public void setAlto(int alto){
		this.alto=alto;
	}
	public int getAlto(){
		return alto;
	}
	
	@Override
	//Muy rudimentario y solo a modo demostrativo, para uso serio debe ser mejorada
	public boolean dentroFigura(Point p) {
		int difX=Math.abs(p.x-(posicion.x + (ancho/2)));
		int difY=Math.abs(p.y-(posicion.y +(ancho/2)));
		return ( (difX<ancho/2) && (difY<ancho/2));   
	}
	
	@Override
	public void dibujar(Graphics g)
	{
		int max = Math.max(origen.length()*10, destino.length()*10);
		setAncho((max>40)?max:40);
		
		g.setColor(Color.BLUE);
		
		g.fillRect(this.getX()-this.getAncho(), this.getY(), this.getAncho(), this.getAlto()); // cuadrado izquierdo
		g.setColor(Color.black);
        g.drawString(origen , getX() - getAncho() + 10, getY()+18);        
        
        g.setColor(Color.BLUE);
		setAncho((escrito.length()*10>40)?escrito.length()*10:40);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAlto()); // cuadrado central
		g.fillRect(this.getX(), this.getY()+this.getAlto(), this.getAncho(), this.getAlto()); // cuadrado inferior
		g.fillRect(this.getX()+this.getAncho(), this.getY(), max, this.getAlto()); // cuadrado derecho
		g.setColor(Color.black);
        g.drawString(escrito,getX()+ getAncho() - escrito.length()*7 - 5,getY()+70);
        g.drawString(destino,getX()+ getAncho() ,getY()+18);
        
        
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAlto()-20);
		}       
		
	}	
}
