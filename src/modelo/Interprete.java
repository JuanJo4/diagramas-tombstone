package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Interprete extends Figura {
	
	private int ancho;
	private int alto;
	private String interprete;
    private String escrito;
	
    public Interprete(Point posicion, int ancho,int alto, String a, String b) {
		this.posicion=posicion;
		this.ancho=ancho;
		this.alto = alto;
		this.seleccionada=false;  //Deberia estar en el constructor pero por simplicidad
        this.interprete=a;
        this.escrito=b;
        }

	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
	}
	public void setalto(int alto){
		this.alto=alto;
	}
	public int getAlto(){
		return alto;
	}
	@Override
	public boolean dentroFigura(Point p) {
		int difX=Math.abs(p.x-(posicion.x+(ancho/2)));
		int difY=Math.abs(p.y-(posicion.y+(ancho/2)));
		return ( (difX<ancho/2) && (difY<ancho/2));   
	}

	@Override
	public void dibujar(Graphics g) {
		
		int max = Math.max(interprete.length()*10,escrito.length()*10);		
		setAncho((max>40)?max:40);
		
		
		
		g.setColor(Color.RED);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getAlto()); // cuadrado central
		g.fillRect(this.getX(), this.getY() + this.getAlto() - 15, this.getAncho(), this.getAlto()); // cuadrado central
		
		if(this.getSeleccionada()){
			g.setColor(Color.RED);
			g.drawRect(this.getX()+7, this.getY()+7, this.getAncho()-20, this.getAncho()-20);
		}
		 
		 g.setColor(Color.black);
         g.drawString(interprete, getX()+10, getY()+20);
         g.setColor(Color.black);
         g.drawString(escrito, getX()+10, getY()+getAlto()+20);
	}
	
	
	
}
