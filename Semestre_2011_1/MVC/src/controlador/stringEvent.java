package controlador;

public class stringEvent {
	String actionCommand;
	public static final int NUEVO = 0;
	public static final int ABRIR = 1;
	public static final int GUARDAR = 2;
	public static final int SALIR = 3;
	public static final int ATAJOS = 4;
	public static final int ACERCA_DE = 5;
	public static final int AGREGAR_COMPILADOR = 6;
	public static final int AGREGAR_MAQUINA = 7;
	public static final int AGREGAR_PROGRAMA = 8;
	public static final int AGREGAR_INTERPRETE = 9;
	
	public stringEvent(String actionCommand){
		this.actionCommand = actionCommand;
	}
	
	public int getIntEvent(){
		if(actionCommand.equals("Nuevo")){
			return stringEvent.NUEVO;
		}
		if(actionCommand.equals("Abrir")){
			return stringEvent.ABRIR;
		}
		if(actionCommand.equals("Guardar")){
			return stringEvent.GUARDAR;
		}
		if(actionCommand.equals("Salir")){
			return stringEvent.SALIR;
		}
		if(actionCommand.equals("Atajos de teclado")){
			return stringEvent.ATAJOS;
		}
		if(actionCommand.equals("Acerca de")){
			return stringEvent.ACERCA_DE;
		}
		if(actionCommand.equals("Agregar Compilador")){
			return stringEvent.AGREGAR_COMPILADOR;
		}
		if(actionCommand.equals("Agregar Máquina")){
			return stringEvent.AGREGAR_MAQUINA;
		}
		if(actionCommand.equals("Agregar Programa")){
			return stringEvent.AGREGAR_PROGRAMA;
		}
		if(actionCommand.equals("Agregar Interprete")){
			return stringEvent.AGREGAR_INTERPRETE;	
		}
		return -1;
	}	
}
