package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelo.Modelo;
import modelo.Figura;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


import controlador.Controlador;

/*		Eventos del mouse en el panel		*/
class MouseController implements MouseListener, MouseMotionListener {
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseDragged(MouseEvent event) {}
	public void mouseMoved(MouseEvent event) {}
}

/*		Eventos del menu		*/
class MenuActionListener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {}
}

public class Vista extends JPanel {
	static final long serialVersionUID = 0L;
	private Modelo modelo;
	public Controlador controlador;  //IMPORTANTE DEBE SER REGISTRADO O TODO FALLA
	
	JMenuBar menubar = new JMenuBar();
	JMenu menuarchivo = new JMenu("Archivo");
	JMenu menuayuda = new JMenu("Ayuda");
	
	MenuActionListener evMenu = new MenuActionListener(){
		public void actionPerformed(ActionEvent e) {
			evMenuPressed(e);
		}
	};
	
	public Vista(Dimension size, Modelo modelo){
		super();
		this.modelo=modelo;		
		setPreferredSize(size);
		setBackground(Color.white);
		setFocusable(true);
		
		/*		Propiedades del menú		*/
		menuarchivo.setMnemonic('A');
		menuayuda.setMnemonic('u');
		menubar.add(menuarchivo);
		menubar.add(menuayuda);
		
		addMenuItem("Nuevo",'N',KeyEvent.VK_N,menuarchivo);
		addMenuItem("Abrir",'A',KeyEvent.VK_A,menuarchivo);
		addMenuItem("Guardar",'G',KeyEvent.VK_G,menuarchivo);
		menuarchivo.addSeparator();
		addMenuItem("Salir",'S',KeyEvent.VK_S,menuarchivo);
		addMenuItem("Acerca de",'d',KeyEvent.VK_D,menuayuda);

		activeMouseEvent();		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		pintarTodo(g2);
	}
	
	private void pintarTodo(Graphics2D g){
		for (Figura elemento : modelo.getListado()) {
			elemento.dibujar(g);
		}
	}
	
	public void addMenuItem(String label,char mnemonic,int keyAcelerator,JMenu parent){
		JMenuItem menuitem = new JMenuItem(label,mnemonic);
		menuitem.setAccelerator(KeyStroke.getKeyStroke(keyAcelerator, Event.CTRL_MASK));
		menuitem.addActionListener(evMenu);		
		parent.add(menuitem);		
	}
	public JMenuBar getMenuBar(){
		return menubar;
	}
	
	public void evMenuPressed(ActionEvent e){
		if(controlador!=null)
		{
			controlador.evMenuPressed(e);
		}
	}
	
	public void eVmousePressed(MouseEvent ev) {
		if(controlador!=null)
		{
			controlador.eVmousePressed(ev);
		}
	}
	
	public void eVmouseDragged(MouseEvent ev) {
		if(controlador!=null)
		{
			controlador.eVmouseDragged(ev);
		}
	}
	
	public void eVmouseReleased (MouseEvent ev) {
		if(controlador!=null)
		{
			controlador.eVmouseReleased(ev);
		}
	}
	
	public void activeMouseEvent(){
		MouseController mouseControl = new MouseController() {
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
			public void mouseMoved(MouseEvent event) {}
			public void mousePressed(MouseEvent event) {
				eVmousePressed(event);	
			}
			public void mouseReleased(MouseEvent event) {
				eVmouseReleased(event);	
			}
			public void mouseDragged(MouseEvent event) {
				eVmouseDragged(event);	
			}
		};
		this.addMouseListener(mouseControl);
		this.addMouseMotionListener(mouseControl);
	}
	
}
