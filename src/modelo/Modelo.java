package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
	
	private List<Figura> listaFiguras;
	public Modelo(){
		listaFiguras = new ArrayList<Figura>();
	}
	
	public List<Figura> getListado(){
		return listaFiguras;
	}
	
	public void anyadirFigura(Figura f){
		listaFiguras.add(f);
	}
	public void eliminarFigura(Figura f){//zuri
                //listaFiguras.remove(f); NO ME FUNCIONA
                  listaFiguras.removeAll(listaFiguras);//SI FUNCIONA PERO LAS BORRA TODASSSS
        }
	public Figura getFiguraEn(Point p){
		for (Figura elemento : getListado()) {
			if(elemento.dentroFigura(p)){
				elemento.seleccionada=true;
				return elemento;				
			}
		}
		return null;
	}
        public Figura getFiguraSa(Point p){//zuri
		for (Figura elemento : getListado()) {
			if(elemento.dentroFigura(p)){
				elemento.seleccionada=false;
				return elemento;
			}
		}
		return null;
	}
}
