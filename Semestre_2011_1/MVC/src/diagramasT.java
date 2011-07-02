import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import vista.Vista;
import modelo.Modelo;
import controlador.Controlador;



public class diagramasT {
	
	public static void main(String[] args) {
		try{
					
			/*		Creación del frame		*/
			final JFrame frame = new JFrame();
			frame.setTitle("Diagramas de Tombstone");
			frame.setVisible(true);
			frame.setLocation(150,50);
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			
			/*		Creación de barra de menú		*/
			JMenuBar menubar = new JMenuBar();						
			JMenu menuarchivo = new JMenu("Archivo");
			JMenu menuayuda = new JMenu("Ayuda");
			menuarchivo.setMnemonic('A');
			menuayuda.setMnemonic('u');
			menubar.add(menuarchivo);
			menubar.add(menuayuda);
			
			JMenuItem inicionuevo = new JMenuItem("Nuevo",'N');
			JMenuItem inicioabrir = new JMenuItem("Abrir",'A');
			JMenuItem inicioguardar = new JMenuItem("Guardar",'G');
			JMenuItem iniciosalir = new JMenuItem("Salir",'S');
			inicionuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
			inicioabrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
			inicioguardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
			iniciosalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
			
			JMenuItem ayudaacercade = new JMenuItem("Acerca de",'A');
			ayudaacercade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
						
			menuarchivo.add(inicionuevo);
			menuarchivo.add(inicioabrir);
			menuarchivo.add(inicioguardar);
			menuarchivo.addSeparator();
			menuarchivo.add(iniciosalir);
			
			menuayuda.add(ayudaacercade);
			
				
			/*		MVC		*/
		    Modelo modelo = new Modelo();
			Vista vista = new Vista(new Dimension(600,400),modelo);
			final Controlador controlador = new Controlador(modelo,vista);
			vista.controlador=controlador; //Lo registro para su uso, deberia ser un metodo pero por simplificacion
			
			Container guiobjects = frame.getContentPane();		    
			JScrollPane ModelScroll = new JScrollPane(controlador.getVista(),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			guiobjects.setLayout(new BorderLayout());
			guiobjects.add(ModelScroll);
			
			frame.setJMenuBar(menubar);
			frame.pack();
			
		}catch (RuntimeException e){
			exitApplication();
		}

	}

	public static void exitApplication() {
		   System.out.println("Saliendo Adios...");
		   System.exit(0);
        }

}
