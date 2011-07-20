package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelo.Compilador;
import modelo.Modelo;
import modelo.Figura;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;


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
	
	JLabel nameproyect = new JLabel();
	JMenuBar menubar = new JMenuBar();
	JPopupMenu popupmenu = new JPopupMenu();
	JMenu menuarchivo = new JMenu("Archivo");
	JMenu menuayuda = new JMenu("Ayuda");
	
	
	
	public Vista(Dimension size, Modelo modelo){
		super();
		this.modelo=modelo;		
		setPreferredSize(size);
		setBackground(Color.white);
		setFocusable(true);
		
		/*		Propiedades del men� y popupmenu		*/
		menuarchivo.setMnemonic('A');
		menuayuda.setMnemonic('u');
		menubar.add(menuarchivo);
		menubar.add(menuayuda);
		
		addMenuItem("Nuevo",KeyEvent.VK_N,menuarchivo,null);
		addMenuItem("Abrir",KeyEvent.VK_A,menuarchivo,null);
		addMenuItem("Guardar",KeyEvent.VK_G,menuarchivo,null);
		menuarchivo.addSeparator();
		addMenuItem("Salir",KeyEvent.VK_S,menuarchivo,null);
		addMenuItem("Atajos de teclado",KeyEvent.VK_T,menuayuda,null);
		addMenuItem("Acerca de",KeyEvent.VK_D,menuayuda,null);
		
		addMenuItem("Agregar Compilador",KeyEvent.VK_C,null,popupmenu);
		addMenuItem("Agregar M�quina",KeyEvent.VK_M,null,popupmenu);
		addMenuItem("Agregar Programa",KeyEvent.VK_P,null,popupmenu);
		addMenuItem("Agregar Interprete",KeyEvent.VK_I,null,popupmenu);			
	}
	
	public void setNameProyect(String name){
		nameproyect.setText("Compilador: " + name);
		nameproyect.setBounds(0,this.getHeight()-20,this.getWidth(),20);
		nameproyect.setVisible(true);
		this.add(nameproyect);
		this.repaint();
	}
	
	public String getNameProyect(){
		return nameproyect.getText();
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
	
	public void addMenuItem(String label,int keyAcelerator,JMenu parent, JPopupMenu parentpopup){
		JMenuItem menuitem;
		
		menuitem = new JMenuItem(label,new ImageIcon("img/"+ label +".png"));
		menuitem.setHorizontalTextPosition(JMenuItem.RIGHT);
		
		menuitem.setAccelerator(KeyStroke.getKeyStroke(keyAcelerator, Event.CTRL_MASK));
		MenuActionListener evMenu = new MenuActionListener(){
			public void actionPerformed(ActionEvent e) {
				evMenuPressed(e);
			}
		};
		
		menuitem.addActionListener(evMenu);	
		
		if(parent!=null)
			parent.add(menuitem);
		else{
			parentpopup.add(menuitem);
			popupmenu.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
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
	
	public void showPopupmenu(MouseEvent mouseEvent){
		if (popupmenu.isPopupTrigger(mouseEvent)) 
			popupmenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());		
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
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent event) {
				showPopupmenu(event);	
			}
			public void mouseReleased(MouseEvent event) {
				showPopupmenu(event);	
			}
		});
		
	}
	
}
