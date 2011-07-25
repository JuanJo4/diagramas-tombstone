package modelo;

import java.awt.*;

public class Programa extends Figura {
	
	private int radio;
	private String nombre;
    private String escrito;
	
    public Programa(Point posicion, int radio, String a, String b) {
		this.posicion=posicion;
		this.radio=radio;
		this.seleccionada=false;  //Deberia estar en el constructor de figura pero por simplicidad
        this.nombre=a;
        this.escrito=b;
        }
	
	public void setRadio(int ancho){
		this.radio=ancho;
	}
	
	public int getRadio(){
		return radio;
	}
	
	@Override
	public boolean dentroFigura(Point p) {
		if ( radio >= Math.sqrt( Math.pow( p.x - posicion.x, 2 ) + Math.pow(p.y - posicion.y, 2 )))		
			return true;
		else
			return false;
	}

	@Override
	public void dibujar(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(this.getX(), this.getY(), this.getRadio()+10, this.getRadio()+10);
		g.fillRect(this.getX()+5, this.getY()+this.getRadio(), this.getRadio(), this.getRadio());
		
		if(this.getSeleccionada()){
			g.setColor(Color.CYAN);
			g.drawOval(this.getX()+7, this.getY()+7, this.getRadio()-20, this.getRadio()-20);
		}
		 g.setColor(Color.black);
         g.drawString(nombre, getX()+15, getY()+20);
         g.setColor(Color.black);
         g.drawString(escrito, getX()+15, getY()+45);
		
	}

}
