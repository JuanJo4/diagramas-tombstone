package modelo;

import java.awt.*;

public class Programa extends Figura {
	
	private int radio;
	private int ancho;
	private String nombre;
    private String escrito;
	
    public Programa(Point posicion, int radio, int ancho,String a, String b) {
		this.posicion=posicion;
		this.radio=radio;
		this.ancho=ancho;
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
	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	public int getAncho(){
		return ancho;
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
		
		setRadio((nombre.length()*10>40)?nombre.length()*5:40);
		
		g.setColor(Color.ORANGE);
		g.fillOval(this.getX(), this.getY(), this.getRadio()+15, this.getRadio()+15);
		//setAncho((escrito.length()*10>40)?escrito.length()*10:40);
		g.fillRect(this.getX()+this.getRadio()/4, this.getY()+this.getRadio() + 2, this.getAncho(), this.getAncho());
		
		if(this.getSeleccionada()){
			g.setColor(Color.CYAN);
			g.drawOval(this.getX()+7, this.getY()+7, this.getRadio()-20, this.getRadio()-20);
		}
		 g.setColor(Color.black);
         g.drawString(nombre, this.getX()+15, this.getY()+this.getRadio()/2 + 10);
         g.setColor(Color.black);
         g.drawString(escrito, this.getX()+this.getAncho()/2, this.getY()+ this.getRadio()+40 - 10);
		
	}

}
