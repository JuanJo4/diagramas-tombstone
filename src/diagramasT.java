import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import modelo.Modelo;
import vista.Vista;
import controlador.Controlador;

public class diagramasT {
	
	public static void main(String[] args) {
		try{
					
			/*		Creaci�n del frame		*/
			final JFrame frame = new JFrame();
			frame.setTitle("Diagramas de Tombstone");
			frame.setVisible(true);
			frame.setLocation(150,50);
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);		
			
			/*		MVC		*/
		    Modelo modelo = new Modelo();
			Vista vista = new Vista(new Dimension(700,500),modelo);
			final Controlador controlador = new Controlador(modelo,vista);
			vista.controlador=controlador; //Lo registro para su uso, deberia ser un metodo pero por simplificacion
			
			Container guiobjects = frame.getContentPane();		    
			JScrollPane ModelScroll = new JScrollPane(controlador.getVista(),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			guiobjects.setLayout(new BorderLayout());
			guiobjects.add(ModelScroll);
			
			frame.setJMenuBar(controlador.getVista().getMenuBar());
			frame.pack();
			
			//System.out.println("hola");
			
		}catch (RuntimeException e){
			exitApplication();
		}

	}

	public static void exitApplication() {
		   System.out.println("Saliendo Adios...");
		   System.exit(0);
        }

}
